// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.IntakeChanPart2;
import edu.wpi.first.wpilibj2.command.CommandBase;

/** An example command that uses an example subsystem. */
public class IntakeSetCmd extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final IntakeChanPart2 m_intakeChanPart2;
  private final double speed;
  private final double idle;

  /**
   * Creates a new cubeIntakeCmd.
   *
   * @param subsystem The subsystem used by this command.
   */
  public IntakeSetCmd(IntakeChanPart2 intakeChanPart2, double vroom, double idol) {
    this.m_intakeChanPart2 = intakeChanPart2;
    this.speed = vroom;
    this.idle = idol;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(intakeChanPart2);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    System.out.println("IntakeSetCmd Started");
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_intakeChanPart2.setMotor(speed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_intakeChanPart2.setMotor(idle); // continue to feed forward when idle to counter gravity
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
