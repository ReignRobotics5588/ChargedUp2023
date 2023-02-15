package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

import frc.robot.subsystems.DriveSubsystem;

public class AutoDrive extends SequentialCommandGroup{
    private static final double FOWARD_DISTANCE = 60;
    private static final double FOWARD_SPEED = 0.7;
    public AutoDrive(DriveSubsystem drive) {
        addCommands(
            new DriveDistance(drive, 70, 0.7));
    }


}
