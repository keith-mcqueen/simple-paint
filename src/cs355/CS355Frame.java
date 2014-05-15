/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cs355;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Talonos
 */
class CS355Frame extends javax.swing.JFrame implements KeyListener {

	private static CS355Frame instance;
	private Color selectedColor = Color.BLACK;
	private Set<Integer> keysPressed = new TreeSet<>();
	Semaphore protectKeyList = new Semaphore(1);

	static CS355Frame inst() {
		if (instance == null) {
			throw new IllegalStateException("Tried to get instance of CS355Frame without initializing it first!");
		}
		return instance;
	}

	public static void createCS355Frame(CS355Controller c, ViewRefresher viewRefresher, MouseListener mouseListener, MouseMotionListener mml) {
		instance = new CS355Frame(c, viewRefresher, mouseListener, mml);
		instance.setVisible(true);
	}

	static boolean isInitialized() {
		return (instance != null);
	}

	CS355Controller controller;


	Thread keyPollingThread;
	private final ViewRefresher viewRefresher;

	private CS355Frame(CS355Controller c, ViewRefresher viewRefresher, MouseListener mouseListener, MouseMotionListener mml) {
		this.viewRefresher = viewRefresher;
		initComponents();
		controller = c;
		RedrawRoutine r = RedrawRoutine.inst();
		r.initialize(canvas1, viewRefresher);
		canvas1.addKeyListener(this);
		jButton1.addKeyListener(this);
		jButton2.addKeyListener(this);
		jButton3.addKeyListener(this);
		jButton4.addKeyListener(this);
		jButton5.addKeyListener(this);
		jButton6.addKeyListener(this);
		jButton7.addKeyListener(this);
		jButton8.addKeyListener(this);
		jButton9.addKeyListener(this);
		jButton10.addKeyListener(this);
		jButton11.addKeyListener(this);
		jScrollBar1.addKeyListener(this);
		jScrollBar2.addKeyListener(this);
		canvas1.addMouseListener(mouseListener);
		canvas1.addMouseMotionListener(mml);
		setSelectedColor(new Color(128, 128, 128, 0));
		keyPollingThread = new Thread(new pollingThread(this));
		keyPollingThread.start();
	}

	/**
	 * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The
	 * content of this method is always regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
	// <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
	private void initComponents() {

		canvas1 = new java.awt.Canvas();
		canvas1 = new CS355Canvas();
		jButton1 = new javax.swing.JButton();
		jButton2 = new javax.swing.JButton();
		jButton3 = new javax.swing.JButton();
		jButton4 = new javax.swing.JButton();
		jButton5 = new javax.swing.JButton();
		jButton6 = new javax.swing.JButton();
		jButton7 = new javax.swing.JButton();
		jButton8 = new javax.swing.JButton();
		jButton9 = new javax.swing.JButton();
		jButton10 = new javax.swing.JButton();
		jScrollBar1 = new javax.swing.JScrollBar();
		jScrollBar2 = new javax.swing.JScrollBar();
		canvas2 = new java.awt.Canvas();
		canvas2 = new CS355SmallCanvas();
		jButton11 = new javax.swing.JButton();
		jButton12 = new javax.swing.JButton();
		jMenuBar1 = new javax.swing.JMenuBar();
		jMenu1 = new javax.swing.JMenu();
		jMenuItem1 = new javax.swing.JMenuItem();
		jMenuItem2 = new javax.swing.JMenuItem();
		jMenuItem3 = new javax.swing.JMenuItem();
		jMenu2 = new javax.swing.JMenu();
		jMenuItem4 = new javax.swing.JMenuItem();
		jMenuItem5 = new javax.swing.JMenuItem();
		jMenuItem6 = new javax.swing.JMenuItem();
		jMenuItem7 = new javax.swing.JMenuItem();
		jMenuItem8 = new javax.swing.JMenuItem();
		jMenuItem9 = new javax.swing.JMenuItem();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

		canvas1.setMaximumSize(new java.awt.Dimension(512, 512));
		canvas1.setMinimumSize(new java.awt.Dimension(512, 512));
		canvas1.setPreferredSize(new java.awt.Dimension(512, 512));

		jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cs355/res/Colors.png"))); // NOI18N
		jButton1.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton1ActionPerformed(evt);
			}
		});

		jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cs355/res/Line.png"))); // NOI18N
		jButton2.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton2ActionPerformed(evt);
			}
		});

		jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cs355/res/Square.png"))); // NOI18N
		jButton3.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton3ActionPerformed(evt);
			}
		});

		jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cs355/res/Rectangle.png"))); // NOI18N
		jButton4.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton4ActionPerformed(evt);
			}
		});

		jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cs355/res/Circle.png"))); // NOI18N
		jButton5.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton5ActionPerformed(evt);
			}
		});

		jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cs355/res/Ellipse.png"))); // NOI18N
		jButton6.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton6ActionPerformed(evt);
			}
		});

		jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cs355/res/Triangle.png"))); // NOI18N
		jButton7.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton7ActionPerformed(evt);
			}
		});

		jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cs355/res/Select.png"))); // NOI18N
		jButton8.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton8ActionPerformed(evt);
			}
		});

		jButton9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cs355/res/ZoomIn.png"))); // NOI18N
		jButton9.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton9ActionPerformed(evt);
			}
		});

		jButton10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cs355/res/ZoomOut.png"))); // NOI18N
		jButton10.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton10ActionPerformed(evt);
			}
		});

		jScrollBar1.setMaximum(2047);
		jScrollBar1.setVisibleAmount(2047);
		jScrollBar1.addAdjustmentListener(new java.awt.event.AdjustmentListener() {
			public void adjustmentValueChanged(java.awt.event.AdjustmentEvent evt) {
				jScrollBar1AdjustmentValueChanged(evt);
			}
		});

		jScrollBar2.setMaximum(2047);
		jScrollBar2.setOrientation(javax.swing.JScrollBar.HORIZONTAL);
		jScrollBar2.setVisibleAmount(2047);
		jScrollBar2.addAdjustmentListener(new java.awt.event.AdjustmentListener() {
			public void adjustmentValueChanged(java.awt.event.AdjustmentEvent evt) {
				jScrollBar2AdjustmentValueChanged(evt);
			}
		});

		jButton11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cs355/res/House.png"))); // NOI18N
		jButton11.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton11ActionPerformed(evt);
			}
		});

		jButton12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cs355/res/Camera.png"))); // NOI18N
		jButton12.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton12ActionPerformed(evt);
			}
		});

		jMenu1.setText("File");

		jMenuItem1.setText("Load");
		jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jMenuItem1ActionPerformed(evt);
			}
		});
		jMenu1.add(jMenuItem1);

		jMenuItem2.setText("Save");
		jMenuItem2.setToolTipText("");
		jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jMenuItem2ActionPerformed(evt);
			}
		});
		jMenu1.add(jMenuItem2);

		jMenuItem3.setText("Quit");
		jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jMenuItem3ActionPerformed(evt);
			}
		});
		jMenu1.add(jMenuItem3);

		jMenuBar1.add(jMenu1);

		jMenu2.setText("Edit");

		jMenuItem4.setText("Brightness");
		jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jMenuItem4ActionPerformed(evt);
			}
		});
		jMenu2.add(jMenuItem4);

		jMenuItem5.setText("Contrast");
		jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jMenuItem5ActionPerformed(evt);
			}
		});
		jMenu2.add(jMenuItem5);

		jMenuItem6.setText("Blur (Uniform)");
		jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jMenuItem6ActionPerformed(evt);
			}
		});
		jMenu2.add(jMenuItem6);

		jMenuItem7.setText("Blur (Median)");
		jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jMenuItem7ActionPerformed(evt);
			}
		});
		jMenu2.add(jMenuItem7);

		jMenuItem8.setText("Sharpen");
		jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jMenuItem8ActionPerformed(evt);
			}
		});
		jMenu2.add(jMenuItem8);

		jMenuItem9.setText("Detect Edges");
		jMenuItem9.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jMenuItem9ActionPerformed(evt);
			}
		});
		jMenu2.add(jMenuItem9);

		jMenuBar1.add(jMenu2);

		setJMenuBar(jMenuBar1);

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
								.addContainerGap()
								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
										.addGroup(layout.createSequentialGroup()
												.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
														.addComponent(jButton9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
														.addComponent(jButton8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
														.addComponent(jButton7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
														.addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
														.addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
														.addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
														.addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
														.addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
														.addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
														.addComponent(jButton10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
												.addGap(10, 10, 10))
										.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
												.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
														.addComponent(jButton12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
														.addComponent(jButton11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
										.addComponent(jScrollBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(canvas2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
										.addComponent(jScrollBar2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(canvas1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
								.addContainerGap())
		);
		layout.setVerticalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(layout.createSequentialGroup()
								.addContainerGap()
								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
										.addGroup(layout.createSequentialGroup()
												.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
														.addComponent(jScrollBar2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(canvas2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
												.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
														.addComponent(jScrollBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
														.addGroup(layout.createSequentialGroup()
																.addComponent(canvas1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
																.addGap(0, 0, Short.MAX_VALUE))))
										.addGroup(layout.createSequentialGroup()
												.addComponent(jButton1)
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
												.addComponent(jButton2)
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
												.addComponent(jButton3)
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
												.addComponent(jButton4)
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
												.addComponent(jButton5)
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
												.addComponent(jButton6)
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
												.addComponent(jButton7)
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
												.addComponent(jButton8)
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
												.addComponent(jButton9)
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
												.addComponent(jButton10)
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
												.addComponent(jButton11)
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
												.addComponent(jButton12)
												.addGap(0, 0, Short.MAX_VALUE)))
								.addContainerGap())
		);

		pack();
	}// </editor-fold>//GEN-END:initComponents

	private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
		Color c = JColorChooser.showDialog(rootPane, "Pick a Color; any Color!", Color.yellow);
		controller.colorButtonHit(c);
	}//GEN-LAST:event_jButton1ActionPerformed

	private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
		controller.rectangleButtonHit();
	}//GEN-LAST:event_jButton4ActionPerformed

	private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
		controller.squareButtonHit();
	}//GEN-LAST:event_jButton3ActionPerformed

	private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
		controller.circleButtonHit();
	}//GEN-LAST:event_jButton5ActionPerformed

	private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
		controller.ellipseButtonHit();
	}//GEN-LAST:event_jButton6ActionPerformed

	private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
		controller.triangleButtonHit();
	}//GEN-LAST:event_jButton7ActionPerformed

	private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
		controller.lineButtonHit();
	}//GEN-LAST:event_jButton2ActionPerformed

	private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
		controller.selectButtonHit();
	}//GEN-LAST:event_jButton8ActionPerformed

	private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
		controller.zoomOutButtonHit();
	}//GEN-LAST:event_jButton10ActionPerformed

	private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
		controller.zoomInButtonHit();
	}//GEN-LAST:event_jButton9ActionPerformed

	private void jScrollBar2AdjustmentValueChanged(java.awt.event.AdjustmentEvent evt) {//GEN-FIRST:event_jScrollBar2AdjustmentValueChanged
		controller.hScrollbarChanged(evt.getValue());
	}//GEN-LAST:event_jScrollBar2AdjustmentValueChanged

	private void jScrollBar1AdjustmentValueChanged(java.awt.event.AdjustmentEvent evt) {//GEN-FIRST:event_jScrollBar1AdjustmentValueChanged
		controller.vScrollbarChanged(evt.getValue());
	}//GEN-LAST:event_jScrollBar1AdjustmentValueChanged

	private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
		controller.toggle3DModelDisplay();
	}//GEN-LAST:event_jButton11ActionPerformed

	private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
		BufferedImage image = new BufferedImage(512, 512, BufferedImage.TYPE_BYTE_GRAY);
		viewRefresher.refreshView((Graphics2D) image.getGraphics());
		ImageIO.saveImage(image);
	}//GEN-LAST:event_jMenuItem2ActionPerformed

	private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
		BufferedImage image = ImageIO.openImage();
		if (image == null) {
			return;
		}
		image = Grayscaler.grayScale(image);
		controller.doLoadImage(image);
	}//GEN-LAST:event_jMenuItem1ActionPerformed

	private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
		System.exit(0);
	}//GEN-LAST:event_jMenuItem3ActionPerformed

	private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
		String brightnessAmount = JOptionPane.showInputDialog(this,
				"How much do you wish to increase brightness by?", "0");
		try {
			int brightnessAmountNum = Integer.parseInt(brightnessAmount);
			if (brightnessAmountNum > 255 || brightnessAmountNum < -255) {
				JOptionPane.showMessageDialog(this, "That is not a valid number. Please enter a number between -255 and 255.",
						"Unable to change brightness", JOptionPane.ERROR_MESSAGE);
				return;
			}
			controller.doChangeBrightness(brightnessAmountNum);
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(this, "That is not a valid number.",
					"Unable to change brightness", JOptionPane.ERROR_MESSAGE);
		}
	}//GEN-LAST:event_jMenuItem4ActionPerformed

	private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
		String contrastAmount = JOptionPane.showInputDialog(this,
				"How much do you wish to increase contrast by?", "0");
		try {
			int contrastAmountNum = Integer.parseInt(contrastAmount);
			if (contrastAmountNum > 100 || contrastAmountNum < -100) {
				JOptionPane.showMessageDialog(this, "That is not a valid number. Please enter a number between -100 and 100.",
						"Unable to change contrast", JOptionPane.ERROR_MESSAGE);
				return;
			}
			controller.doChangeContrast(contrastAmountNum);
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(this, "That is not a valid number.",
					"Unable to change contrast", JOptionPane.ERROR_MESSAGE);
		}
	}//GEN-LAST:event_jMenuItem5ActionPerformed

	private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
		controller.doUniformBlur();
	}//GEN-LAST:event_jMenuItem6ActionPerformed

	private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
		controller.doMedianBlur();
	}//GEN-LAST:event_jMenuItem7ActionPerformed

	private void jMenuItem8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem8ActionPerformed
		controller.doSharpen();
	}//GEN-LAST:event_jMenuItem8ActionPerformed

	private void jMenuItem9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem9ActionPerformed
		controller.doEdgeDetection();
	}//GEN-LAST:event_jMenuItem9ActionPerformed

	private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
		controller.toggleBackgroundDisplay();
	}//GEN-LAST:event_jButton12ActionPerformed

	/**
	 * @param args the command line arguments
	 */
	public static void main(String args[]) {
	      /* Set the Nimbus look and feel */
		//<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
	      /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(CS355Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(CS355Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(CS355Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(CS355Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
		//</editor-fold>

        /* Create and display the form */
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new CS355Frame(null, null, null, null).setVisible(true);
			}
		});
	}

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private java.awt.Canvas canvas1;
	private java.awt.Canvas canvas2;
	private javax.swing.JButton jButton1;
	private javax.swing.JButton jButton10;
	private javax.swing.JButton jButton11;
	private javax.swing.JButton jButton12;
	private javax.swing.JButton jButton2;
	private javax.swing.JButton jButton3;
	private javax.swing.JButton jButton4;
	private javax.swing.JButton jButton5;
	private javax.swing.JButton jButton6;
	private javax.swing.JButton jButton7;
	private javax.swing.JButton jButton8;
	private javax.swing.JButton jButton9;
	private javax.swing.JMenu jMenu1;
	private javax.swing.JMenu jMenu2;
	private javax.swing.JMenuBar jMenuBar1;
	private javax.swing.JMenuItem jMenuItem1;
	private javax.swing.JMenuItem jMenuItem2;
	private javax.swing.JMenuItem jMenuItem3;
	private javax.swing.JMenuItem jMenuItem4;
	private javax.swing.JMenuItem jMenuItem5;
	private javax.swing.JMenuItem jMenuItem6;
	private javax.swing.JMenuItem jMenuItem7;
	private javax.swing.JMenuItem jMenuItem8;
	private javax.swing.JMenuItem jMenuItem9;
	private javax.swing.JScrollBar jScrollBar1;
	private javax.swing.JScrollBar jScrollBar2;
	// End of variables declaration//GEN-END:variables

	void setScrollAttribute(CS355SScrollbarAttrConsts scrollBar, CS355SScrollbarAttrConsts attribute, int number) {
		JScrollBar scrollbarToChange = null;
		switch (scrollBar) {
			case H_SCROLL_BAR:
				scrollbarToChange = jScrollBar2;
				break;
			case V_SCROLL_BAR:
				scrollbarToChange = jScrollBar1;
				break;
			default:
				throw new IllegalArgumentException("Bad scrollbar type!");

		}
		switch (attribute) {
			case MAX:
				scrollbarToChange.setMaximum(number);
				break;
			case MIN:
				scrollbarToChange.setMinimum(number);
				break;
			case KNOB:
				scrollbarToChange.setVisibleAmount(number);
				break;
			case POSIT:
				scrollbarToChange.setValue(number);
				break;
			default:
				throw new IllegalArgumentException("Bad Attribute type!");

		}
	}

	void setSelectedColor(Color c) {
		selectedColor = c;
		try {
			Thread.sleep(100);
		} catch (InterruptedException ex) {
			Logger.getLogger(CS355Frame.class.getName()).log(Level.SEVERE, null, ex);
		}
		Graphics2D toDrawOn = (Graphics2D) canvas2.getGraphics();
		toDrawOn.setColor(Color.BLACK);
		toDrawOn.fillRect(0, 0, canvas2.getSize().width, canvas2.getSize().height);
		toDrawOn.setColor(Color.white);
		toDrawOn.fillOval(1, 1, canvas2.getSize().width - 2, canvas2.getSize().height - 2);
		toDrawOn.setColor(c);
		toDrawOn.fillRect(0, 0, canvas2.getSize().width, canvas2.getSize().height);
	}

	void setSelectedColor() {
		setSelectedColor(selectedColor);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		//Typing doesn't do anything.
	}

	@Override
	public void keyPressed(KeyEvent e) {
		//This is a set instead of a list because holding down a key will call
		//this function many times in a row. You cannot guarentee that there is
		//only one press per release like you can with a click.
		protectKeyList.acquireUninterruptibly();
		keysPressed.add(e.getKeyCode());
		protectKeyList.release();
	}

	@Override
	public void keyReleased(KeyEvent e) {
		protectKeyList.acquireUninterruptibly();
		keysPressed.remove(e.getKeyCode());
		protectKeyList.release();
	}

	private static class pollingThread implements Runnable {

		CS355Frame parent;

		public pollingThread(CS355Frame parent) {
			this.parent = parent;
		}

		@Override
		public void run() {
			while (true) {
				try {
					Thread.sleep(33);
					if (!parent.keysPressed.isEmpty()) {
						//This is to prevent the form from concurrently modifying
						//the list while iterating through it.
						parent.protectKeyList.acquire();
						parent.controller.keyPressed(parent.keysPressed.iterator());
						parent.protectKeyList.release();
					}
				} catch (InterruptedException ex) {
					Logger.getLogger(CS355Frame.class.getName()).log(Level.SEVERE, null, ex);
				}
			}
		}
	}
}
