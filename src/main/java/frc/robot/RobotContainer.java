// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.subsystems.LimelightSubsystem;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.RunCommand;
import frc.robot.subsystems.*;
import frc.robot.commands.*;

/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in
 * the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of
 * the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final DriveSubsystem m_driveSubsystem = new DriveSubsystem();// declaring new drivesystem object
  private final DriveCameraSubsystem m_driveCameraSubsystem = new DriveCameraSubsystem();
  // private final TurnInPlaceCommand m_autoCommand = new
  // TurnInPlaceCommand(m_driveSubsystem, 90, 0.5);


  //private final AutoDrive m_autoCommand = new AutoDrive(m_driveSubsystem,m_Pneumatics);
  private final DriveDistance m_autoCommand = new DriveDistance(m_driveSubsystem, 40, 0.85);


  //private final AutoBalanceBackwards m_autoCommand = new AutoBalanceBackwards(m_driveSubsystem);
  public static LimelightSubsystem m_LimelightSubsystem = new LimelightSubsystem();
  public static PneumaticBrakeAndCompressor m_Pneumatics = new PneumaticBrakeAndCompressor();
  public static ArmSubsystem m_ArmSubsystem = new ArmSubsystem();

  public static XboxController driverXBox = new XboxController(1);
  public static XboxController operatorController = new XboxController(2);
  public static PneumaticGrabber m_PneumaticGrabber = new PneumaticGrabber();

  private static final int A_BUTTON_XBOX = 1;
  private static final int B_BUTTON_XBOX = 2;
  private static final int X_BUTTON_XBOX = 3;
  private static final int Y_BUTTON_XBOX = 4;
  private static final int LEFT_BUMPER_XBOX = 5;
  private static final int RIGHT_BUMPER_XBOX = 6;
  private static final int BACK_ARROW = 7;
  private static final int START_ARROW = 8;
  private static final int JOYSTICK_RIGHT_CLICK = 10;
  private static final int JOYSTICK_LEFT_CLICK = 9;

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();
    m_driveSubsystem.setDefaultCommand(
        new RunCommand(() -> m_driveSubsystem.tankDrive(driverXBox.getRawAxis(1), driverXBox.getRawAxis(5)),
            m_driveSubsystem));

    m_ArmSubsystem.setDefaultCommand(
      new RunCommand(() -> m_ArmSubsystem.setArmSpeed(operatorController.getRawAxis(1), operatorController.getRawAxis(5)), m_ArmSubsystem));
    // ^ Setting the Default Command to m_robotDrive, meaning it will drive as long
    // as nothing else is scheduled

    /*
     * Climber climber_command = new Climber(m_ClimberSubsystem,
     * operatorController.getRawAxis(1) * Constants.CLIMBER_SPEED_MULTIPLIER);
     * m_ClimberSubsystem.setDefaultCommand(climber_command);
     */
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be
   * created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing
   * it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    JoystickButton brakeOn = new JoystickButton(driverXBox, A_BUTTON_XBOX);
    brakeOn.toggleOnTrue(new BrakeCommandOn(m_Pneumatics));

    JoystickButton brakeOff = new JoystickButton(driverXBox, B_BUTTON_XBOX);
    brakeOff.toggleOnTrue(new BrakeCommandOff(m_Pneumatics));

    JoystickButton grabberOn = new JoystickButton(operatorController, X_BUTTON_XBOX);
    grabberOn.toggleOnTrue(new InstantCommand(() -> m_PneumaticGrabber.grabberSetOn(),m_PneumaticGrabber));

    JoystickButton grabberOff = new JoystickButton(operatorController, Y_BUTTON_XBOX);
    grabberOff.toggleOnTrue(new InstantCommand(() -> m_PneumaticGrabber.grabberSetOff(),m_PneumaticGrabber));

  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    m_driveSubsystem.resetEncoders();
 
    return m_autoCommand;
  }
}
