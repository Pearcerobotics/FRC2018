package org.usfirst.frc.team1745.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

public class Lift {
	TalonSRX lLift, rLift;
	
	int currentSetPos = 100;
	
	public Lift()
	{
		lLift = new TalonSRX(28);
		rLift = new TalonSRX(29);
		
		lLift.follow(rLift);
		
		lLift.setInverted(false);
		
		rLift.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 10);
		rLift.setSensorPhase(true);
		rLift.setInverted(true);
		
		rLift.config_kP(0, .7, 10);
		rLift.config_kI(0, .000006, 10);
		rLift.config_kD(0, .3, 10);
	}
	
	public void setPos(int pos)
	{
		currentSetPos = pos;
	}
	
	public void control()
	{
		rLift.set(ControlMode.Position, currentSetPos);
	}

	public int getPos() {
		// TODO Auto-generated method stub
		return currentSetPos;
	}
}
