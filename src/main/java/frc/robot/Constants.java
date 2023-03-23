/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

public final class Constants {
    public static double k = 0.75;// in DriveSubsystem, changes speed for some reason
    public static final double K_TURN = 0.25;
    public static final double MAX_SPEED = 0.25;
    public static final int SMART_LIMIT = 80;
    public static final double ARM_SPEED = 0.55;
    public static final double FORWARD_DISTANCE = 130;
    public static final double FORWARD_SPEED = 0.7;
    public static final double upperEncoderAdjust = 0.0;
    public static final double lowerEncoderAdjust = 0.0;
    public static final double ARM_POSITION_ERROR = 0.01;
    public static final double UPPER_ARM_SCORE_POSITION = -0.7472;
    public static final double LOWER_ARM_SCORE_POSITION = 0.7189;
}
