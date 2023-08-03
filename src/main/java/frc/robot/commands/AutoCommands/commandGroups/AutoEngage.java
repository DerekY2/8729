// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.AutoCommands.commandGroups;
import frc.robot.commands.AutoCommands.AutoDrive;
import frc.robot.subsystems.DriveSubsystem;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
/** An example command that uses an example subsystem. */
public class AutoEngage extends SequentialCommandGroup {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  
  private final DriveSubsystem m_driveSubsystem;
  //private final LimelightSubsystem m_limelightSubsystem;


  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public AutoEngage(DriveSubsystem m_driveSubsystem) {
    this.m_driveSubsystem = m_driveSubsystem;

    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(m_driveSubsystem);

    addCommands(
      
        // engage
      new AutoDrive(m_driveSubsystem, true, false, 3.0, 0.0, 0.6, 0.0), 
      new AutoDrive(m_driveSubsystem, false, true, 0.0, 2800, 0.55, 0.0),
      new AutoDrive(m_driveSubsystem, false, true, 0.0, 430, 0.0, 0.4)

    );
  }
}

