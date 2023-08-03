// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import frc.robot.Constants.LimelightConstants;
import edu.wpi.first.math.geometry.Pose2d;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

import edu.wpi.first.wpilibj.smartdashboard.Field2d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;



public class LimelightSubsystem extends SubsystemBase {

  private NetworkTable m_limelightTable;

  private final Field2d m_field = new Field2d();

  private Pose2d robotPose;
  private double id;

  NetworkTableEntry tx, ty, ta, tv;
  double x, y, area, targetAvailable;
  

  /** Creates a new ExampleSubsystem. */
  public LimelightSubsystem() {

    SmartDashboard.putData(m_field);

    m_limelightTable = NetworkTableInstance.getDefault().getTable("limelight");
  }

  public void getNetworkTableValues(){
    
    tx = m_limelightTable.getEntry("tx");
    ty = m_limelightTable.getEntry("ty");
    ta = m_limelightTable.getEntry("ta"); 
    tv = m_limelightTable.getEntry("tv"); 

    //read values periodically
    x = tx.getDouble(LimelightConstants.defaultValue);
    y = ty.getDouble(LimelightConstants.defaultValue);
    area = ta.getDouble(LimelightConstants.defaultValue);
    targetAvailable = tv.getDouble(LimelightConstants.defaultValue);

    //post to smart dashboard periodically
    SmartDashboard.putNumber("LimelightX", x);
    SmartDashboard.putNumber("LimelightY", y);
    SmartDashboard.putNumber("LimelightArea", area);
    SmartDashboard.putNumber("Target Available", targetAvailable);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    getNetworkTableValues();
  }

  public double getLimelightX(){
    return x;
  }

  public double getLimelightY(){
    return y;
  }


  public Pose2d getRobotPose(){
    return robotPose; 
  }
    
  public double getRobotAngle(){
    if(id != -1){
      return robotPose.getRotation().getDegrees();
    }
    return -1;
  }
  


  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}