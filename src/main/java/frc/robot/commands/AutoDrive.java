package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

import frc.robot.subsystems.DriveSubsystem;
import frc.robot.commands.DriveDistance;
import frc.robot.subsystems.PneumaticBrakeAndCompressor;
import frc.robot.commands.BrakeCommandOn;
import frc.robot.commands.BrakeCommandOff;
import frc.robot.Constants;

public class AutoDrive extends SequentialCommandGroup{
    public AutoDrive(DriveSubsystem drive, PneumaticBrakeAndCompressor brake) {
        addCommands(
            new DriveDistance(drive, Constants.FORWARD_DISTANCE, Constants.FORWARD_SPEED),
            new AutoBalanceForward(drive),
            new BrakeCommandOn(brake)
            );
    }


}
