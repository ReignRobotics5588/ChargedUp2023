package frc.robot.subsystems;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
//import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
//import com.revrobotics.SparkMaxAlternateEncoder.Type;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxAbsoluteEncoder;
import com.revrobotics.SparkMaxLimitSwitch;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMax.IdleMode;
import frc.robot.Constants;
import edu.wpi.first.math.filter.SlewRateLimiter;
import com.revrobotics.SparkMaxAbsoluteEncoder.Type;

public class ArmSubsystem extends SubsystemBase {

    private CANSparkMax upperJoint;
    private CANSparkMax lowerJoint;

    //private RelativeEncoder m_upperJointEncoder;
    //private RelativeEncoder m_lowerJointEncoder;

    private SparkMaxPIDController m_upperPID;
    private SparkMaxPIDController m_lowerPID;

    private SparkMaxLimitSwitch m_UpperForwardLimitSwitch;
    private SparkMaxLimitSwitch m_UpperBackwardsLimitSwitch;
    private SparkMaxLimitSwitch m_LowerForwardLimitSwitch;
    private SparkMaxLimitSwitch m_LowerBackwardsLimitSwitch;

    private SparkMaxAbsoluteEncoder m_upperJointEncoder;
    private SparkMaxAbsoluteEncoder m_lowerJointEncoder;


    private int kCPR = 8192;

    private SlewRateLimiter upperSlew = new SlewRateLimiter(3.0);
    private SlewRateLimiter lowerSlew = new SlewRateLimiter(3.0);


  /** Creates a new ExampleSubsystem. */
  public ArmSubsystem() {
    upperJoint = new CANSparkMax(11, MotorType.kBrushless);
    lowerJoint = new CANSparkMax(12, MotorType.kBrushless);
    lowerJoint.setInverted(true);
    upperJoint.setInverted(false);
    m_upperJointEncoder = upperJoint.getAbsoluteEncoder(Type.kDutyCycle);
    m_lowerJointEncoder = lowerJoint.getAbsoluteEncoder(Type.kDutyCycle);

    m_upperJointEncoder.setInverted(true);
    //m_upperJointEncoder = upperJoint.getAlternateEncoder(Type.kQuadrature, kCPR);
    //m_lowerJointEncoder = lowerJoint.getAlternateEncoder(Type.kQuadrature, kCPR);

    //m_upperPID = upperJoint.getPIDController();
    //m_upperPID.setFeedbackDevice(m_upperJointEncoder);

    //m_lowerPID = lowerJoint.getPIDController();
    //m_lowerPID.setFeedbackDevice(m_lowerJointEncoder);

    upperJoint.setIdleMode(IdleMode.kBrake);
    lowerJoint.setIdleMode(IdleMode.kBrake);

    m_UpperForwardLimitSwitch = upperJoint.getForwardLimitSwitch(SparkMaxLimitSwitch.Type.kNormallyOpen);
  //  m_UpperForwardLimitSwitch.enableLimitSwitch(false);
    m_UpperBackwardsLimitSwitch = upperJoint.getReverseLimitSwitch(SparkMaxLimitSwitch.Type.kNormallyOpen);
  //  m_UpperBackwardsLimitSwitch.enableLimitSwitch(false);
    m_LowerForwardLimitSwitch = lowerJoint.getForwardLimitSwitch(SparkMaxLimitSwitch.Type.kNormallyOpen);
//    m_LowerForwardLimitSwitch.enableLimitSwitch(false);
    m_LowerBackwardsLimitSwitch = lowerJoint.getReverseLimitSwitch(SparkMaxLimitSwitch.Type.kNormallyOpen);
 //   m_LowerBackwardsLimitSwitch.enableLimitSwitch(false);
  }
  
  public double getUpperPosition(){
    return m_upperJointEncoder.getPosition();
  }

  public double getLowerPosition(){
    return m_lowerJointEncoder.getPosition();
  }

  public void setUpperMotorSpeed(double speed){
    upperJoint.set(speed * Constants.ARM_SPEED);
  }

  public void setLowerMotorSpeed(double speed){
    lowerJoint.set(speed * Constants.ARM_SPEED);
  }

  /*public void driveArm(double upperSpeed, double lowerSpeed){
    setUpperMotorSpeed(upperMotor.calculate(upperSpeed));
    setLowerMotorSpeed(lowerMotor.calculate(lowerSpeed));
  }*/

  public void driveArm(double upperSpeed, double lowerSpeed){
    setUpperMotorSpeed(upperSpeed);
    setLowerMotorSpeed(lowerSpeed);
  }

  public boolean getLowerBackwardsSwitch(){
   return m_LowerBackwardsLimitSwitch.isPressed();
  }

  public boolean getUpperForwardsSwitch(){
    return m_UpperForwardLimitSwitch.isPressed();
   }

   public boolean getUpperBackwardsSwitch(){
    return m_UpperBackwardsLimitSwitch.isPressed();
   }
 
   public boolean getLowerForwardsSwitch(){
     return m_LowerForwardLimitSwitch.isPressed();
    }

   public void setArmSpeed(double lowerSpeed, double upperSpeed){
   //upperSpeed=-upperSpeed;//test

    SmartDashboard.putNumber("Upper Speed", upperSpeed);
    SmartDashboard.putNumber("Lower Speed", lowerSpeed);
    setLowerMotorSpeed(lowerSlew.calculate(lowerSpeed));
    setUpperMotorSpeed(upperSlew.calculate(upperSpeed));
/* 
    if(getLowerBackwardsSwitch() && (lowerSpeed < 0.0)){
      setLowerMotorSpeed(0.0);
    }
    else setLowerMotorSpeed(lowerSpeed * Constants.ARM_SPEED);

    if(getLowerForwardsSwitch() && (lowerSpeed > 0.0)){
      setLowerMotorSpeed(0.0);
    }
    else  setLowerMotorSpeed(lowerSpeed * Constants.ARM_SPEED);

    if(getUpperForwardsSwitch() && (upperSpeed > 0.0)){
      setUpperMotorSpeed(0.0);
      SmartDashboard.putBoolean("Upper Forward Stop", true);
    }
    else if(getUpperBackwardsSwitch() && (upperSpeed < 0.0)){
      setUpperMotorSpeed(0.0);
      SmartDashboard.putBoolean("Upper Forward Stop", true);
    }
    else
    {
        setUpperMotorSpeed(upperSpeed * Constants.ARM_SPEED);
        SmartDashboard.putBoolean("Upper Forward Stop", false);
    }
    */
  }

  public double getUpperEncoderPosition(){
    return m_upperJointEncoder.getPosition() + Constants.upperEncoderAdjust;
  }

  public double getLowerEncoderPosition(){
    return m_lowerJointEncoder.getPosition() + Constants.lowerEncoderAdjust;
  }
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putBoolean("Upper Joint Limit Switch Forward", m_UpperForwardLimitSwitch.isPressed());
    SmartDashboard.putBoolean("Upper Joint Limit Switch Backwards", m_UpperBackwardsLimitSwitch.isPressed());
    SmartDashboard.putBoolean("Lower Joint Limit Switch Forward", m_LowerForwardLimitSwitch.isPressed());
    SmartDashboard.putBoolean("Lower Joint Limit Switch Backwards", m_LowerBackwardsLimitSwitch.isPressed());

    SmartDashboard.putNumber("Upper Encoder", getUpperEncoderPosition());
    SmartDashboard.putNumber("Lower Encoder", getLowerEncoderPosition());

  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
