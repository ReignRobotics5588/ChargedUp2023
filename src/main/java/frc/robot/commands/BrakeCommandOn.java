package frc.robot.commands;

import frc.robot.subsystems.PneumaticBrakeAndCompressor;
import edu.wpi.first.wpilibj2.command.CommandBase;

/** An example command that uses an example subsystem. */
public class BrakeCommandOn extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final PneumaticBrakeAndCompressor m_Pneumatics;

  /**
   * Creates a new BrakeCommand.
   *
   * @param Pneumatics The subsystem used by this command.
   */
  public BrakeCommandOn(PneumaticBrakeAndCompressor subsystem) {
    m_Pneumatics = subsystem;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(subsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_Pneumatics.brakeSetOn();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {}

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
