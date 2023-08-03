// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.LimelightSubsystem;
import frc.robot.subsystems.DriveSubsystem;
import edu.wpi.first.wpilibj2.command.CommandBase;

/** An example command that uses an example subsystem. */
public class AlignmentCmd extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final DriveSubsystem m_driveSubsystem;
  private final LimelightSubsystem m_limelightSubsystem;
  double error;
  double xSpeed;

  /**
   * Creates a new AlignmentCmd.
   *
   * @param subsystem The subsystem used by this command.
   */
  public AlignmentCmd(DriveSubsystem driveSubsystem,LimelightSubsystem limelightSubsystem, double speed) {
    m_driveSubsystem = driveSubsystem;
    m_limelightSubsystem = limelightSubsystem;
    xSpeed = speed;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(limelightSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    System.out.println("AlignmentCmd Started");
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    error = m_limelightSubsystem.getLimelightX();//correct until error is near 0.

    if (error > 0) {
      m_driveSubsystem.drive(xSpeed, 0.0);
    }
    else if (error < 0) {
      m_driveSubsystem.drive(-xSpeed, 0.0);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_driveSubsystem.drive(0.0, 0.0);
    System.out.println("AlignmentCmd Ended");
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if (-4 < error && error < 0) {
      return true;
    }
    else{
      return false;
    }
  }
}
