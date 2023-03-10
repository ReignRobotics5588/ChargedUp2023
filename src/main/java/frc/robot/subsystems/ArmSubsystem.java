package frc.robot.subsystems;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
//import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.SparkMaxAlternateEncoder.Type;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxLimitSwitch;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMax.IdleMode;
import frc.robot.Constants;

public class ArmSubsystem extends SubsystemBase {

    private CANSparkMax upperJoint;
    private CANSparkMax lowerJoint;

    private RelativeEncoder m_upperJointEncoder;
    private RelativeEncoder m_lowerJointEncoder;

    private SparkMaxPIDController m_upperPID;
    private SparkMaxPIDController m_lowerPID;

    private SparkMaxLimitSwitch m_UpperForwardLimitSwitch;
    private SparkMaxLimitSwitch m_UpperBackwardsLimitSwitch;
    private SparkMaxLimitSwitch m_LowerForwardLimitSwitch;
    private SparkMaxLimitSwitch m_LowerBackwardsLimitSwitch;


    private int kCPR = 8192;

  /** Creates a new ExampleSubsystem. */
  public ArmSubsystem() {
    upperJoint = new CANSparkMax(11, MotorType.kBrushless);
    lowerJoint = new CANSparkMax(12, MotorType.kBrushless);
    lowerJoint.setInverted(true);

    //m_upperJointEncoder = upperJoint.getAlternateEncoder(Type.kQuadrature, kCPR);
    //m_lowerJointEncoder = lowerJoint.getAlternateEncoder(Type.kQuadrature, kCPR);

    //m_upperPID = upperJoint.getPIDController();
    //m_upperPID.setFeedbackDevice(m_upperJointEncoder);

    //m_lowerPID = lowerJoint.getPIDController();
    //m_lowerPID.setFeedbackDevice(m_lowerJointEncoder);

    upperJoint.setIdleMode(IdleMode.kBrake);
    lowerJoint.setIdleMode(IdleMode.kBrake);

    m_UpperForwardLimitSwitch = upperJoint.getForwardLimitSwitch(SparkMaxLimitSwitch.Type.kNormallyOpen);
    m_UpperBackwardsLimitSwitch = upperJoint.getReverseLimitSwitch(SparkMaxLimitSwitch.Type.kNormallyOpen);
    m_LowerForwardLimitSwitch = lowerJoint.getForwardLimitSwitch(SparkMaxLimitSwitch.Type.kNormallyOpen);
    m_LowerBackwardsLimitSwitch = lowerJoint.getReverseLimitSwitch(SparkMaxLimitSwitch.Type.kNormallyOpen);

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

  public void driveArm(double upperSpeed, double lowerSpeed){
    setUpperMotorSpeed(upperSpeed);
    setLowerMotorSpeed(lowerSpeed);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putBoolean("Upper Joint Limit Switch Forward", m_UpperForwardLimitSwitch.isPressed());
    SmartDashboard.putBoolean("Upper Joint Limit Switch Backwards", m_UpperBackwardsLimitSwitch.isPressed());
    SmartDashboard.putBoolean("Lower Joint Limit Switch Forward", m_LowerForwardLimitSwitch.isPressed());
    SmartDashboard.putBoolean("Lower Joint Limit Switch Backwards", m_LowerBackwardsLimitSwitch.isPressed());

  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
