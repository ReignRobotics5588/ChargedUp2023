package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

//for odometry
import edu.wpi.first.math.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.math.kinematics.DifferentialDriveKinematics;
import edu.wpi.first.math.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
//for NavX + SmartDashboard
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.SPI;


public class Odometry extends SubsystemBase{

    DriveSubsystem m_drive = new DriveSubsystem();

    Pose2d m_pose = new Pose2d();
    AHRS m_ahrs = new AHRS(SPI.Port.kMXP);

    DifferentialDriveOdometry m_odometry;

    double m_angularVelocity;
    double m_linearVelocity;

    public Odometry(){



        Rotation2d rotation2D = new Rotation2d(m_ahrs.getYaw());
        m_odometry = new DifferentialDriveOdometry(rotation2D, m_drive.getLeftEncoderDistance(), m_drive.getRighttEncoderDistance(), m_pose);

        //9.85in(?) gear ratio, 6in diameter wheel 
        //wheel speed-> chassis speed

        DifferentialDriveKinematics kinematics = new DifferentialDriveKinematics(0.1524);
        DifferentialDriveWheelSpeeds wheelSpeeds = new DifferentialDriveWheelSpeeds(6.0,6.0);
        ChassisSpeeds chassisSpeed = kinematics.toChassisSpeeds(wheelSpeeds);

        m_linearVelocity = chassisSpeed.vxMetersPerSecond;
        m_angularVelocity = chassisSpeed.omegaRadiansPerSecond;

    }

    @Override
    public void periodic() {
    // This method will be called once per scheduler run
        Rotation2d currentRotation = new Rotation2d(m_ahrs.getYaw());
        m_pose = m_odometry.update(currentRotation,
        m_drive.getLeftEncoderDistance(),
        m_drive.getRighttEncoderDistance());

        SmartDashboard.putNumber("Odometry X : ", m_pose.getX()); // 
        SmartDashboard.putNumber("Odometry Y : ", m_pose.getY()); //
        
    }


    
}
