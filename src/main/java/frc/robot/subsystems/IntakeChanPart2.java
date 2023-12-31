// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.


// Intake INTAKE 


package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class IntakeChanPart2 extends SubsystemBase {
  /** Creates a new IntakeSubsystem. */
  /** Creates a new ElevatorSubsystem. */
 
  final CANSparkMax INTAKE = new CANSparkMax(3, MotorType.kBrushless);
  // initialize built-in encoder from NEO (right only)

  public IntakeChanPart2() {
    
  }

  public void setMotor(double speed){ // -1 <= speed <= 1
    INTAKE.set(speed); 
  }


  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }




  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
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
}
