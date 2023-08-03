// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.DriveSubsystem;
import edu.wpi.first.wpilibj2.command.CommandBase;


/** An example command that uses an example subsystem. */
public class SlowDriveCmd extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final DriveSubsystem m_driveSubsystem;
  private double slowDrive;
  private double slowTurn;

  /**
   * Creates a new SlowDriveCmd.
   *
   * @param subsystem The subsystem used by this command.
   */
  public SlowDriveCmd(DriveSubsystem m_driveSubsystem, double slowDrive, double slowTurn) {
    this.m_driveSubsystem = m_driveSubsystem;
    this.slowDrive = slowDrive;
    this.slowTurn = slowTurn;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(m_driveSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    System.out.println("SlowDriveCmd Initialized");
    m_driveSubsystem.toggleSlowDrive(slowDrive, slowTurn); // change the speed multiplier for the entire DriveSubsystem
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    System.out.println("Executing SlowDriveCmd");
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
   
    System.out.println("SlowDriveCmd Ended");
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return true;
  }
}
