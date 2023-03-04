// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.RelativeEncoder;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import frc.robot.RobotContainer;

//use w/ armsubsystem?
public class Switch extends SubsystemBase {
  /** Creates a new ExampleSubsystem. */
  private DigitalInput m_switch;

  public Switch() {
    m_switch = new DigitalInput(5);
    //public RelativeEncoder m_encoder = climber.getEncoder();
  }

  public boolean getSwitch() {
    return m_switch.get();
  }
/* 
  public double getEncoderDistance() {
    return (m_encoder.getPosition() / 42);// 42 ticks in one rotation, counting how many full rotations there are :)
  }
*/
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
