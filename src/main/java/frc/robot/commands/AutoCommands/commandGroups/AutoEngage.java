// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.AutoCommands.commandGroups;
import frc.robot.commands.AutoCommands.AutoDrive;
import frc.robot.commands.EncoderDrive;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.Constants.Engage;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
/** An example command that uses an example subsystem. */
public class AutoEngage extends SequentialCommandGroup {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  
  //private final LimelightSubsystem m_limelightSubsystem;


  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public AutoEngage(DriveSubsystem m_driveSubsystem) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(m_driveSubsystem);

    addCommands(
      
        // engage
      new AutoDrive(m_driveSubsystem, Engage.kDriveSpeed, Engage.kTurnSpeed).withTimeout(Engage.kDriveTimeout), 
      new EncoderDrive(m_driveSubsystem, Engage.kEncoderDriveSpeed, Engage.kEncoderTurnSpeed, Engage.kEncoderDriveDistance, Engage.kEndInverted1),
      new EncoderDrive(m_driveSubsystem, Engage.kEncoderAdjustSpeed, Engage.kEncoderAdjustTurn, Engage.kEncoderAdjustDistance, Engage.kEndInverted2)

    );
  }
}

