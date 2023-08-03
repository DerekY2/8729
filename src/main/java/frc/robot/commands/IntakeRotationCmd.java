// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.IntakeChanPart1;
import frc.robot.Constants.IntakePart1Constants;
import edu.wpi.first.wpilibj2.command.CommandBase;

/** An example command that uses an example subsystem. */
public class IntakeRotationCmd extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final IntakeChanPart1 m_intakeChanPart1;
  private double speed;

  /**
   * Creates a new IntakeRotationCmd.
   *
   * @param subsystem The subsystem used by this command.
   */
  public IntakeRotationCmd(IntakeChanPart1 subsystem, double speed) {
    this.m_intakeChanPart1 = subsystem;
    this.speed = speed;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(subsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    System.out.println("IntakeRotationCmd initialized");
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_intakeChanPart1.setMotor(speed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_intakeChanPart1.setMotor(IntakePart1Constants.idol); // continue to feed forward while idle to counter gravity
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
