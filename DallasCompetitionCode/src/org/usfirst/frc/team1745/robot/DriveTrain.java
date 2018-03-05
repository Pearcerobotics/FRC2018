package org.usfirst.frc.team1745.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

public class DriveTrain {

	TalonSRX lfDrive,
			lmDrive,
			lbDrive,
			rfDrive,
			rmDrive,
			rbDrive;
	
	public DriveTrain()
	{
		lfDrive = new TalonSRX(40);
		lmDrive = new TalonSRX(41);
		lbDrive = new TalonSRX(42);
		
		rfDrive = new TalonSRX(43);
		rmDrive = new TalonSRX(44);
		rbDrive = new TalonSRX(45);
		
		lmDrive.follow(lfDrive);
		lbDrive.follow(lfDrive);
		
		rmDrive.follow(rfDrive);
		rbDrive.follow(rfDrive);
		
		rfDrive.setInverted(true);
		rmDrive.setInverted(true);
		rbDrive.setInverted(true);
		
		lfDrive.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 10);
		rfDrive.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 10);
		
		lfDrive.setSensorPhase(true);
		rfDrive.setSensorPhase(true);
		
	}
	
	public int getLeftPos()
	{
		return lfDrive.getSelectedSensorPosition(0);
	}
	
	public int getRightPos()
	{
		return rfDrive.getSelectedSensorPosition(0);
	}
	
	public void setDrive(double left, double right)
	{
		lfDrive.set(ControlMode.PercentOutput, left);
		rfDrive.set(ControlMode.PercentOutput, right);
	}
	
}
