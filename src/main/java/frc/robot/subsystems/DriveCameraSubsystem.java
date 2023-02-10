package frc.robot.subsystems;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.cscore.UsbCamera;



public class DriveCameraSubsystem {
    
    public DriveCameraSubsystem()
    {   
        
       UsbCamera camera = CameraServer.startAutomaticCapture("strawberrcam", 0);
       camera.setResolution(320, 240)
       camera.setFPS(10)
    }

}
