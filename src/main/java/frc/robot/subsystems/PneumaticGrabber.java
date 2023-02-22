package frc.robot.subsystems;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;



public class PneumaticGrabber extends SubsystemBase {
    DoubleSolenoid grabberSolenoid = new DoubleSolenoid(PneumaticsModuleType.REVPH, 2, 7 );
    

  public PneumaticGrabber() {
    grabberSolenoid.set(Value.kForward);
   }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }


  //unsure about this -- dont completely know if this is right values -- ask zach or larry on monday :)

  //We can support all the functions supported by solenoid by most likely we will only care about toggle() 
  // since that just changes the state (ex: in -> out or out -> in) -zach

  public void grabberSetOff(){
    grabberSolenoid.set(Value.kReverse);
   
  }

  public void grabberSetOn(){
    grabberSolenoid.set(Value.kForward);
    
  }

  public void grabberBrake(){
    grabberSolenoid.toggle();

  }

}
