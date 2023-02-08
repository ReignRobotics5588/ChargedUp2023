package frc.robot.subsystems;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.PneumaticsModuleType;



public class Pneumatics extends SubsystemBase {
    Compressor phCompressor = new Compressor(1, PneumaticsModuleType.REVPH);
    DoubleSolenoid leftbrakeSolenoidPH = new DoubleSolenoid(PneumaticsModuleType.REVPH, 0, 1 );
    //DoubleSolenoid rightbrakeSolenoidPH = new DoubleSolenoid(PneumaticsModuleType.REVPH, 2, 3 );
    /** Creates a new ExampleSubsystem. */
  public Pneumatics() {
    leftbrakeSolenoidPH.set(Value.kReverse);}
    //rightbrakeSolenoidPH.set(Value.kReverse);}

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
    leftbrakeSolenoidPH.set(Value.kOff);
    //rightbrakeSolenoidPH.set(Value.kOff);
  }

  public void setOut(){
    leftbrakeSolenoidPH.set(Value.kForward);
    //rightbrakeSolenoidPH.set(Value.kForward);
  }

  public void toggleBrake(){
    leftbrakeSolenoidPH.toggle();
    //rightbrakeSolenoidPH.toggle();
  }
}
