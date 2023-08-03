// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.AutoCommands.commandGroups;

import frc.robot.commands.AutoCommands.AutoIntakeRotation;
import frc.robot.commands.AutoCommands.AutoElevator;
import frc.robot.commands.AutoCommands.AutoDrive;
import frc.robot.commands.AutoCommands.AutoSetIntake;

import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.ElevatorSubsystem;
import frc.robot.subsystems.IntakeChanPart1;
import frc.robot.subsystems.IntakeChanPart2;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
/** An example command that uses an example subsystem. */
public class AutoConeMobility extends SequentialCommandGroup {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  
  private final DriveSubsystem m_driveSubsystem;
  private final ElevatorSubsystem m_elevatorSubsystem;
  private final IntakeChanPart1 m_intakeChanPart1;
  private final IntakeChanPart2 m_intakeChanPart2;
  //private final LimelightSubsystem m_limelightSubsystem;


  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public AutoConeMobility(DriveSubsystem m_driveSubsystem, ElevatorSubsystem m_elevatorSubsystem, IntakeChanPart1 m_intakeChanPart1, IntakeChanPart2 m_intakeChanPart2) {
    this.m_driveSubsystem = m_driveSubsystem;
    this.m_elevatorSubsystem = m_elevatorSubsystem;
    this.m_intakeChanPart1 = m_intakeChanPart1;
    this.m_intakeChanPart2 = m_intakeChanPart2;

    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(m_driveSubsystem, m_elevatorSubsystem, m_intakeChanPart1, m_intakeChanPart2);

    addCommands(
        
      // shoot cone, then exit community zone
      new AutoIntakeRotation(m_intakeChanPart1, 0.3, -0.5),
      new AutoElevator(m_elevatorSubsystem, -1.0/2),
      new AutoElevator(m_elevatorSubsystem, 0.1, 0.3/2),
      new AutoIntakeRotation(m_intakeChanPart1, 0.7, 0.9),
      new AutoSetIntake(m_intakeChanPart2, 0.5, -0.9),
      new AutoIntakeRotation(m_intakeChanPart1, 0.7, -0.6),
      new AutoElevator(m_elevatorSubsystem, 1.5, 0.8/2),
      new AutoDrive(m_driveSubsystem, true, false, 2.0, 0.0, 0.65, 0.0),
      new AutoDrive(m_driveSubsystem, false, true, true, 0.0, -1240, 0.55, 0.0)

    );
  }
}

