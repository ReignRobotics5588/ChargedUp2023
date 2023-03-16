package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

import frc.robot.subsystems.DriveSubsystem;
import frc.robot.commands.DriveDistance;
import frc.robot.subsystems.PneumaticBrakeAndCompressor;
import frc.robot.subsystems.PneumaticGrabber;
import frc.robot.commands.BrakeCommandOn;
import frc.robot.commands.BrakeCommandOff;
import frc.robot.commands.AutoBalance;
import frc.robot.Constants;

public class AutoDrive extends SequentialCommandGroup{
    public AutoDrive(DriveSubsystem drive, PneumaticBrakeAndCompressor brake, PneumaticGrabber grabber) {
        addCommands(
            new InstantCommand(() -> grabber.grabberSetOff(), grabber),
            new DriveDistance(drive, Constants.FORWARD_DISTANCE, Constants.FORWARD_SPEED),
            new DriveDistance(drive,62, -Constants.FORWARD_SPEED),
            new AutoBalance(drive),
            new BrakeCommandOff(brake)//BrakeCommandOff turns the brake on
            );
    }
}
