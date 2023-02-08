/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
/*
*limelight
*/
package frc.robot.subsystems;

//import com.revrobotics.CANEncoder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import frc.robot.Constants;
import edu.wpi.first.math.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.math.kinematics.DifferentialDriveKinematics;

import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.SPI;

//work on https://docs.wpilib.org/en/stable/docs/software/kinematics-and-odometry/intro-and-chassis-speeds.html
import edu.wpi.first.math.kinematics.ChassisSpeeds;

public class DriveSubsystem extends SubsystemBase {

  //https://docs.wpilib.org/en/stable/docs/software/kinematics-and-odometry/intro-and-chassis-speeds.html
  public static DifferentialDrive m_drive;

  private CANSparkMax frontLeftMotor = new CANSparkMax(1, MotorType.kBrushless);
  private CANSparkMax frontRightMotor = new CANSparkMax(2, MotorType.kBrushless);
  private CANSparkMax backLeftMotor = new CANSparkMax(3, MotorType.kBrushless);
  private CANSparkMax backRightMotor = new CANSparkMax(4, MotorType.kBrushless);

  private RelativeEncoder m_frontLeftEncoder = frontLeftMotor.getEncoder();
  private RelativeEncoder m_frontRightEncoder = frontRightMotor.getEncoder();
  private RelativeEncoder m_backRightEncoder = backRightMotor.getEncoder();
  private RelativeEncoder m_backLeftEncoder = backLeftMotor.getEncoder();

  AHRS m_ahrs = new AHRS(SPI.Port.kMXP);

  DifferentialDriveOdometry m_odometry;

  //may need to reset here

  Pose2d m_pose;

  public DriveSubsystem() {
    frontLeftMotor.setInverted(true);
    frontRightMotor.setInverted(false);
    backLeftMotor.setInverted(true);
    backRightMotor.setInverted(false);
    m_backLeftEncoder.setPositionConversionFactor(1.9);
    m_backRightEncoder.setPositionConversionFactor(1.9);
    m_frontLeftEncoder.setPositionConversionFactor(1.9);
    m_frontRightEncoder.setPositionConversionFactor(1.9);

    frontLeftMotor.setIdleMode(IdleMode.kBrake);
    frontRightMotor.setIdleMode(IdleMode.kBrake);
    backLeftMotor.setIdleMode(IdleMode.kBrake);
    backRightMotor.setIdleMode(IdleMode.kBrake);

    // ^ FIX: Making sure none of the motors are inverted, change when we figure out
    // WTH is up with the motors lol

    frontLeftMotor.setSmartCurrentLimit(Constants.SMART_LIMIT);
    frontRightMotor.setSmartCurrentLimit(Constants.SMART_LIMIT);
    backLeftMotor.setSmartCurrentLimit(Constants.SMART_LIMIT);
    backRightMotor.setSmartCurrentLimit(Constants.SMART_LIMIT);

    backLeftMotor.follow(frontLeftMotor);
    backRightMotor.follow(frontRightMotor);

    this.resetEncoders();

    // ???? Configure encoders here

    m_drive = new DifferentialDrive(frontLeftMotor, frontRightMotor);

    Rotation2d rotation2D = new Rotation2d((double)m_ahrs.getYaw());
    m_pose = new Pose2d();
    m_odometry = new DifferentialDriveOdometry(rotation2D, getLeftEncoderDistance(), getRighttEncoderDistance(), m_pose);
    // ((Object) m_drive).setRightSideInverted(false);
    m_drive.setMaxOutput(.80);
  }

  public void arcadeDrive(double speed, double rotation) {
    m_drive.arcadeDrive(speed * Constants.k, rotation);
  }

  public void tankDrive(double leftSpeed, double rightSpeed) {
    // May need invert left
    m_drive.tankDrive(leftSpeed * Constants.k, rightSpeed * Constants.k);

  }

  @Override
  public void periodic() {
    m_drive.feedWatchdog(); // check this
    Rotation2d currentRotation = new Rotation2d(m_ahrs.getYaw());
    m_pose = m_odometry.update(currentRotation,
    getLeftEncoderDistance(),
    getRighttEncoderDistance());

    SmartDashboard.putNumber("Odometry X : ", m_pose.getX()); // 
    SmartDashboard.putNumber("Odometry Y : ", m_pose.getY()); // 


  }

  public void resetEncoders() {
    m_frontLeftEncoder.setPosition(0.0);
    m_frontRightEncoder.setPosition(0.0);
    m_backLeftEncoder.setPosition(0.0);
    m_backRightEncoder.setPosition(0.0);
  }

  public double getMeanEncoderDistance() {
    // currently report leaders only
    return (getLeftEncoderDistance() + getRighttEncoderDistance()) / 2.0;
  }

  public double getLeftEncoderDistance() {

    // currently report leader only
    return m_frontLeftEncoder.getPosition();
  }

  public double getRighttEncoderDistance() {
    // currently report leader only
    return m_frontRightEncoder.getPosition();
  }

  // May want to try this rather than multiplying by constant scale everywhere
  public void setMaxOutput(double maxOutput) {
    m_drive.setMaxOutput(maxOutput);
  }

}
