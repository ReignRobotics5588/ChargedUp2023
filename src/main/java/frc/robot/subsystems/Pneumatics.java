package frc.robot.subsystems;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.PneumaticsModuleType;



public class Pneumatics extends SubsystemBase {
    Compressor phCompressor = new Compressor(1, PneumaticsModuleType.REVPH);
    Solenoid brakeSolenoidPH = new Solenoid(PneumaticsModuleType.REVPH, 1);
    /** Creates a new ExampleSubsystem. */
  public Pneumatics() {}

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }

  public void enableCompressor(){
    phCompressor.enableDigital();
  }

  public void disable(){
    phCompressor.disable();
  }

  public boolean getPressureSwitchValue(){
    return phCompressor.getPressureSwitchValue();
  }

  public double getCompressorCurrent(){
    return phCompressor.getCurrent();
  }

  public void setOut(){
    brakeSolenoidPH.set(true);
  }

  public void setIn(){
    brakeSolenoidPH.set(false);
  }

  public void toggleBrake(){
    brakeSolenoidPH.toggle();
  }
}
