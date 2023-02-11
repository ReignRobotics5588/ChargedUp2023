package frc.robot.subsystems;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;



public class PneumaticBrakeAndCompressor extends SubsystemBase {
    Compressor phCompressor = new Compressor(1, PneumaticsModuleType.REVPH);
    DoubleSolenoid brakeSolenoidPH = new DoubleSolenoid(PneumaticsModuleType.REVPH, 5, 6 );
    

  public PneumaticBrakeAndCompressor() {
    brakeSolenoidPH.set(Value.kForward);
   }

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
  //kReverse --> brake enabled, kForward --> brake disabled
  //We can support all the functions supported by solenoid by most likely we will only care about toggle() 
  // since that just changes the state (ex: in -> out or out -> in) -zach

  public void brakeSetOff(){
    brakeSolenoidPH.set(Value.kReverse);
   
  }

  public void brakeSetOn(){
    brakeSolenoidPH.set(Value.kForward);
    
  }

  public void toggleBrake(){
    brakeSolenoidPH.toggle();

  }

}
