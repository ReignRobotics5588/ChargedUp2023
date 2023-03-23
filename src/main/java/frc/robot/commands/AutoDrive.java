package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.subsystems.ArmSubsystem;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.commands.DriveDistance;
import frc.robot.subsystems.PneumaticBrakeAndCompressor;
import frc.robot.subsystems.PneumaticGrabber;
import frc.robot.commands.BrakeCommandOn;
import frc.robot.commands.BrakeCommandOff;
import frc.robot.commands.AutoBalance;
import frc.robot.Constants;

public class AutoDrive extends SequentialCommandGroup{
    public AutoDrive(DriveSubsystem drive, PneumaticBrakeAndCompressor brake, PneumaticGrabber grabber, ArmSubsystem arm) {
        addCommands(
            //new MoveUpperArm(arm, arm.getUpperEncoderPosition() + Constants.UPPER_ARM_SCORE_POSITION),
            //new MoveLowerArm(arm, arm.getLowerEncoderPosition() + Constants.LOWER_ARM_SCORE_POSITION),
           
            new InstantCommand(() -> grabber.grabberSetOff(), grabber),
            new WaitCommand(1.5),
            //new MoveLowerArm(arm, arm.getLowerEncoderPosition() - Constants.LOWER_ARM_SCORE_POSITION),
            //new MoveUpperArm(arm, arm.getUpperEncoderPosition() - Constants.UPPER_ARM_SCORE_POSITION),
            new DriveDistance(drive, Constants.FORWARD_DISTANCE, Constants.FORWARD_SPEED),
            new DriveDistance(drive, 70, -8),
            new AutoBalance(drive),
            new BrakeCommandOff(brake)//BrakeCommandOff turns the brake on
            );
    }
}
