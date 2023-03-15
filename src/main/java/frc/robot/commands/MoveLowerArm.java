package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ArmSubsystem;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.Constants;

import java.io.Console;

import edu.wpi.first.wpilibj.RobotState;

public class MoveLowerArm extends CommandBase {
  @SuppressWarnings({ "PMD.UnusedPrivateField", "PMD.SingularField" })
  private final ArmSubsystem m_arm;
  private double m_target;

  public MoveLowerArm(ArmSubsystem a, double target) {
    m_arm = a;
    m_target = target;

    addRequirements(m_arm);
  }
    

  @Override
  public void initialize() {
    
  }


  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
   /*  while(m_driveSubsystem.getGyroPitch() >= -2 || m_driveSubsystem.getGyroPitch() <= 2){
      SmartDashboard.putNumber("state", 1);
      m_driveSubsystem.tankDrive(0.4, 0.4);
    }*/
    
    //while(!(Math.abs(m_target - m_arm.getLowerPosition()) <= Constants.ARM_POSITION_ERROR)){
      if(m_arm.getLowerEncoderPosition() > m_target){

        m_arm.setArmSpeed(-(Constants.ARM_SPEED), 0.0);

      }

      else if(m_arm.getLowerEncoderPosition() < m_target){

        m_arm.setArmSpeed((Constants.ARM_SPEED), 0.0);

      }
      
      else{
      end(true);
      }

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    //m_driveSubsystem.tankDrive(0, 0);
    m_arm.setArmSpeed(0,0);
  }

  // Returns true when the command should end.
  @Override 
  public boolean isFinished() {
    SmartDashboard.putNumber("LOWERARMERROR", m_target-m_arm.getLowerPosition());
    return Math.abs(m_target - m_arm.getLowerPosition()) <= Constants.ARM_POSITION_ERROR;
  }
}
