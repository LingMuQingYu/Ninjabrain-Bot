package ninjabrainbot.gui;

import java.awt.Color;
import java.awt.event.ActionEvent;

import javax.swing.Timer;

import ninjabrainbot.gui.components.ThrowPanel;
import ninjabrainbot.gui.components.ThemedComponent;

public class TextAnimator implements ThemedComponent {

	int i;
	Timer timer;
	Color start, end;
	int duration;
	ThrowPanel jtp;

	public TextAnimator(GUI gui, int durationMillis) {
		gui.registerThemedComponent(this);
		this.start = Color.WHITE;
		this.end = Color.WHITE;
		this.duration = durationMillis;
	}

	public void setJThrowPanel(ThrowPanel jtp) {
		i = 0;
		if (timer != null) {
			this.jtp.setForeground(end);
			timer.stop();
		}
		this.jtp = jtp;
		timer = new Timer(duration / 100, (ActionEvent event) -> {
			jtp.setForeground(getInterpolatedColor((float) i / 100f));
			i++;
			if (i == 100)
				timer.stop();
		});
		timer.start();
	}

	private Color getInterpolatedColor(float t) {
		int r = (int) (end.getRed() * t + start.getRed() * (1.0f - t));
		int g = (int) (end.getGreen() * t + start.getGreen() * (1.0f - t));
		int b = (int) (end.getBlue() * t + start.getBlue() * (1.0f - t));
		int a = (int) (end.getAlpha() * t + start.getAlpha() * (1.0f - t));
		return new Color(r, g, b, a);
	}
	
	public void setColors(Color start, Color end) {
		this.start = start;
		this.end = end;
	}

	@Override
	public void updateSize(GUI gui) {
	}

	@Override
	public void updateColors(GUI gui) {
		setColors(gui.theme.TEXT_COLOR_STRONG, gui.theme.TEXT_COLOR_NEUTRAL);
	}

}
