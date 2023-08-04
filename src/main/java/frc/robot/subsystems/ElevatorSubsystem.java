// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;



/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */

public class ElevatorSubsystem extends SubsystemBase {
  /** Creates a new ElevatorSubsystem. */
  boolean toggle = false;
  boolean up = false;

  private final Timer elevatorTimer = new Timer();

  // Initialize encoder
  // final Encoder encoderB = new Encoder(2, 3);

  // Set motor controller connections: ELevator/Intake motor > SparkMax > RoboRIO
  final CANSparkMax m_leftElevator = new CANSparkMax(1, MotorType.kBrushless);
  final CANSparkMax m_rightElevator = new CANSparkMax(2, MotorType.kBrushless);
  // initialize built-in encoder from NEO (right only)
  final RelativeEncoder m_elevatorEncoder = m_rightElevator.getEncoder();

  // Group elevator motors
  final MotorControllerGroup m_elevator = new MotorControllerGroup(m_leftElevator, m_rightElevator);
  // initialize limit switch
  final DigitalInput toplimitswitch = new DigitalInput(0);

  public ElevatorSubsystem(){
    m_rightElevator.setInverted(true);
  }

  public void setMotor(double speed){

    m_elevator.set(speed); // motor speed
  }

  public double getEncoderPosition(){
    return m_elevatorEncoder.getPosition(); // # of rotations
  }

  public boolean getTopLimitSwitch(){
    toggle = toplimitswitch.get();
    return toggle;
  }

  public boolean getUp(){
    return up;
  }

  public void setUp(boolean a){
    up = a;
  }

  public void resetTimer(){
    elevatorTimer.reset();
  }

  public void startTimer(){
    elevatorTimer.start();
  }

  public double getTimer(){
    return elevatorTimer.get();
  }




  
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putNumber("Elevator Encoder", m_elevatorEncoder.getPosition());
    SmartDashboard.putBoolean("Limit Switch", toplimitswitch.get());
  }




  /**
   * Example command factory method.
   *
   * @return a command
   */
  public CommandBase exampleMethodCommand() {
    // Inline construction of command goes here.
    // Subsystem::RunOnce implicitly requires `this` subsystem.
    return runOnce(
        () -> {
          /* one-time action goes here */
        });
  }

  /**
   * An example method querying a boolean state of the subsystem (for example, a digital sensor).
   *
   * @return value of some boolean subsystem state, such as a digital sensor.
   */
  public boolean exampleCondition() {
    // Query some boolean state, such as a digital sensor.
    return false;
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
