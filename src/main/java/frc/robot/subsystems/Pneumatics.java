package frc.robot.subsystems;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.PneumaticsModuleType;



public class Pneumatics extends SubsystemBase {
    Compressor phCompressor = new Compressor(1, PneumaticsModuleType.REVPH);
    DoubleSolenoid brakeSolenoidPH = new DoubleSolenoid(PneumaticsModuleType.REVPH, 1, 0 );
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

  //unsure about this -- dont completely know if this is right values -- ask zach or larry on monday :)

  //We can support all the functions supported by solenoid by most likely we will only care about toggle() 
  // since that just changes the state (ex: in -> out or out -> in) -zach

  public void setOff(){
    brakeSolenoidPH.set(Value.kOff);
  }

  public void setOut(){
    brakeSolenoidPH.set(Value.kForward);
  }

  public void setIn(){
    brakeSolenoidPH.set(Value.kReverse);
  }

  public void toggleBrake(){
    brakeSolenoidPH.toggle();
  }
}
