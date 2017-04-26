import javax.swing.*;

import java.awt.*;

/**
 * This code came from "An Introduction to JAVA programming (9th)" by Y. Daniel Liang
 * and was modified for use by Riley Fant and Lauren Wells
 */

public class Histogram extends JPanel {
  /**
	 * 
	 */
	private static final long serialVersionUID = 4L;
// Count the occurrence of 26 letters
  private int[] count;
  private String[] geos;
  private boolean isVertical;

  /** Set the count and display histogram */
  public void showHistogram(int[] count, String[] geos, boolean isVertical) {
    this.count = count;
    this.geos = geos;
    this.isVertical = isVertical;
    repaint();
  }

  @Override
  protected void paintComponent(Graphics g) {
    if (count == null) return; // No display if count is null

    super.paintComponent(g);
    setFont(new Font("sansserif", Font.BOLD, 12));
    if(!isVertical) {
	    // Find the panel size and bar width and interval dynamically
	    int width = getWidth();
	    int height = getHeight();
	    int interval = (width - 10) / count.length;
	    int individualWidth = (int)(((width - 40) / 24) * 0.60);
	
	    // Find the maximum count. The maximum count has the highest bar
	    int maxCount = 0;
	    for (int i = 0; i < count.length; i++) {
	      if (maxCount < count[i])
	        maxCount = count[i];
	    }
	
	    // x is the start position for the first bar in the histogram
	    int x = 30;
	
	    // Draw a horizontal base line
	    g.drawLine(10, height - 45, width - 10, height - 45);
	    for (int i = 0; i < count.length; i++) {
	      // Find the bar height
	      int barHeight =
	        (int)(((double)count[i] / (double)maxCount) * (height - 55));
	
	      // Display a bar (i.e. rectangle)
	      g.drawRect(x, height - 45 - barHeight, individualWidth,
	        barHeight);
	
	      // Display a letter under the base line
	      
	      g.drawString(geos[i] + "", x, height - 30);
	
	      // Move x for displaying the next character
	      x += interval;
	    }
    }
    else if(isVertical) {
    	 // Find the panel size and bar width and interval dynamically
	    int width = getWidth();
	    int height = getHeight();
	    int x = 30;
	    int y = 30;
	    int individualWidth = (int)(((width - 40) / 24) * 0.60);
	
	    // Find the maximum count. The maximum count has the highest bar
	    int maxCount = 0;
	    for (int i = 0; i < count.length; i++) {
	      if (maxCount < count[i])
	        maxCount = count[i];
	    }
	    
	   //Draw the segments
	    for (int i = 0; i < count.length; i++) {
		      // Find the bar height
		      int barHeight =
		        (int)(((double)count[i] / (double)maxCount) * (height - 55));
		
		      // Display a bar (i.e. rectangle)
		      g.drawRect(x, y, individualWidth, barHeight);
		      
		      //Add labels
		      g.drawString(geos[i], individualWidth + 60, y);
		
		      // Move x for displaying the next character
		      y += barHeight;
		  }
    }
  }

  @Override 
  public Dimension getPreferredSize() {
	  int xmax = 0;
	for (String geo : geos) {
		if (geo.length()>xmax)
			xmax = geo.length();
	}
	int ymax = 0;
	for (int i : count) {
		if (i>ymax)
			ymax = i;
	}
    return new Dimension(xmax*50, ymax + 50);
  }
}
