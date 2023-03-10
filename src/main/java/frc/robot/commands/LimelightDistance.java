package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
//import frc.robot.Constants;
//import frc.robot.Robot;
//import frc.robot.RobotContainer;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.LimelightSubsystem;

public class LimelightDistance extends CommandBase{

    private static final double TARGET_AREA = 1.89;
    private static final double MAX_AREA_ABS_ERROR = 0.005;//UHHHH ASK ZACH

  private DriveSubsystem m_robotDrive;
  private LimelightSubsystem m_limelight;

  public LimelightDistance(LimelightSubsystem limelight, DriveSubsystem robotDrive) {
    m_limelight = limelight;
    m_robotDrive = robotDrive;
    addRequirements(m_limelight, m_robotDrive);
  }
  @Override
  public void initialize() {

  }

  @Override
  public void execute() {
    double minValue = 0.008; //0.001
    double speed = 0.0;
    double heading = 0.0;
    if (!m_limelight.hasTarget()){
      speed = 0.0;
      heading = 0.0;
    } else {
        speed = (TARGET_AREA - m_limelight.getArea()) * 4.00;
        if(speed > 0){
          speed += minValue;
        } else if (speed < 0){
          speed -= minValue;
        }
        
        heading = m_limelight.getX() * 0.04;
        if (heading > 1){
          heading -= minValue;
          speed += 0.1;
        } else if (heading < -1){
          heading += minValue;
          speed += 0.1;
        }
        speed = MathUtil.clamp(speed, -0.60, 0.60);
        heading = MathUtil.clamp(-heading, -1, 1);
        SmartDashboard.putNumber("Distance headed", heading);
        SmartDashboard.putNumber("Speed", speed);
    }
    m_robotDrive.tankDrive(speed, heading);    
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {

  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    
    return !m_limelight.hasTarget() || Math.abs(TARGET_AREA - m_limelight.getArea()) < MAX_AREA_ABS_ERROR;
  }
}

