package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.Constants;

import java.io.Console;

import edu.wpi.first.wpilibj.RobotState;

public class AutoBalance extends CommandBase {
  @SuppressWarnings({ "PMD.UnusedPrivateField", "PMD.SingularField" })
  private final DriveSubsystem m_driveSubsystem;

  public AutoBalance(DriveSubsystem DriveSubsystem) {
    m_driveSubsystem = DriveSubsystem;
  }
    

  @Override
  public void initialize() {
    SmartDashboard.putNumber("Robot Pitch", m_driveSubsystem.getGyroPitch());
    double kP = 0.05;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
   /*  while(m_driveSubsystem.getGyroPitch() >= -2 || m_driveSubsystem.getGyroPitch() <= 2){
      SmartDashboard.putNumber("state", 1);
      m_driveSubsystem.tankDrive(0.4, 0.4);
    }*/
    
    //while(m_driveSubsystem.getGyroPitch() >= 3 || m_driveSubsystem.getGyroPitch() <= -3){
      if(m_driveSubsystem.getGyroPitch() >= 3.5){
        
        SmartDashboard.putNumber("pitch", (m_driveSubsystem.getGyroPitch()));
        SmartDashboard.putNumber("state", 2);

        m_driveSubsystem.tankDrive(-0.35,-0.35);
      }

      else if(m_driveSubsystem.getGyroPitch() <= 3.5){
        
        SmartDashboard.putNumber("pitch", (m_driveSubsystem.getGyroPitch()));
        SmartDashboard.putNumber("state", 2);

        m_driveSubsystem.tankDrive(0.45,0.45);
      }
      
      else{
      end(true);
      }

      /*
      while (m_driveSubsystem.getGyroPitch() <= 6.5) {
        SmartDashboard.putNumber("state", 3);
        m_driveSubsystem.tankDrive(0.25,0.25);*/
      //} //else {
        //end(true);
      //}

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_driveSubsystem.tankDrive(0, 0);
  }

  // Returns true when the command should end.
  @Override 
  public boolean isFinished() {
    SmartDashboard.putString(
      "hello world", "hello team");
    return !RobotState.isAutonomous();
    //return false;



    /*
    if (m_speed > 0) {
      return current >= target;
    } else
      return current <= target;
    */
  }
}
