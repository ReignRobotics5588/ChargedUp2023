package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ArmSubsystem;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.Constants;

import java.io.Console;

import edu.wpi.first.wpilibj.RobotState;

public class MoveUpperArm extends CommandBase {
  @SuppressWarnings({ "PMD.UnusedPrivateField", "PMD.SingularField" })
  private final ArmSubsystem m_arm;
  private double m_target;

  public MoveUpperArm(ArmSubsystem a, double target) {
    m_arm = a;
    m_target = target;

    addRequirements(m_arm);
  }
    

  @Override
  public void initialize() {
    
  }


  @Override
  public void execute() {
    if(m_arm.getUpperEncoderPosition() > m_target){

      m_arm.setArmSpeed(0.0,-(Constants.ARM_SPEED));

    }

    else if(m_arm.getUpperEncoderPosition() < m_target){

      m_arm.setArmSpeed(0.0,(Constants.ARM_SPEED));

    }
    
    else{
    end(true);
    }

  }

  
  @Override
  public void end(boolean interrupted) {
    //m_driveSubsystem.tankDrive(0, 0);
    m_arm.setArmSpeed(0,0);
  }

  @Override 
  public boolean isFinished() {
    SmartDashboard.putNumber("UPPERARMERROR", m_target-m_arm.getUpperPosition());
    return Math.abs(m_target - m_arm.getUpperPosition()) <= Constants.ARM_POSITION_ERROR;
  }
}
