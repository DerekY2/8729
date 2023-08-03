// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.AutoCommands;

import frc.robot.subsystems.ElevatorSubsystem;
import frc.robot.Constants.AutoConstants;
import edu.wpi.first.wpilibj2.command.CommandBase;

/** An example command that uses an example subsystem. */
public class AutoElevator extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final ElevatorSubsystem m_elevatorSubsystem;
  private final double m_speed;
  private double time;
  private final boolean timeout;
  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public AutoElevator(ElevatorSubsystem subsystem, double speed) { // End condition will be the limit switch being pressed
    this.m_elevatorSubsystem = subsystem;
    this.m_speed = speed;
    this.timeout = false;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(subsystem);
  }

  public AutoElevator(ElevatorSubsystem subsystem, double timeout, double speed) { // End condition will be a timer
    this.m_elevatorSubsystem = subsystem;
    this.m_speed = speed;
    this.time = timeout;
    this.timeout = true;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(subsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    System.out.println("AutoElevator Started");
    
    
    if(timeout){
      m_elevatorSubsystem.resetTimer();
      m_elevatorSubsystem.startTimer();
    }
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_elevatorSubsystem.setMotor(m_speed);
    System.out.println(m_elevatorSubsystem.getTimer());
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_elevatorSubsystem.setMotor(AutoConstants.kInterrupted);
    m_elevatorSubsystem.resetTimer();
    System.out.println("AutoElevator Ended");
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if(timeout){
      if(m_elevatorSubsystem.getTimer() >= time){ // end command when timeout reached
        return true;
      }
      else{
        return false;
      }
    }

    else{
      if(m_elevatorSubsystem.getTopLimitSwitch() == true){ // end command when elevator reaches the top
        return true;
      }
      else if(m_elevatorSubsystem.getTopLimitSwitch() == false){
        return false;
      }
      else{
        System.out.println("Error");
        return false;
      }
    }
  }
}
