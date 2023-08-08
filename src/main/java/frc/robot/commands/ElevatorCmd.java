// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.Constants.ElevatorConstants;
import frc.robot.subsystems.ElevatorSubsystem;
import edu.wpi.first.wpilibj2.command.CommandBase;

/** An example command that uses an example subsystem. */
public class ElevatorCmd extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final ElevatorSubsystem m_elevatorSubsystem;
  private double speed;

  /**
   * Creates a new ElevatorDownCmd.
   *
   * @param subsystem The subsystem used by this command.
   */
  public ElevatorCmd(ElevatorSubsystem subsystem, double speed) {
    this.m_elevatorSubsystem = subsystem;
    this.speed = speed;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(subsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    System.out.println("ElevatorDownCmd initialized");
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_elevatorSubsystem.setMotor(speed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_elevatorSubsystem.setMotor(ElevatorConstants.kElevatorInterrupted);
    System.out.println("ElevatorDownCmd Ended");
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if(m_elevatorSubsystem.getTopLimitSwitch()){
      return true;
    }
    else{
      return false;
    }
  }
}
