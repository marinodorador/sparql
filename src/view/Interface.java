package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;

import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.query.ResultSetFormatter;
import com.hp.hpl.jena.rdf.model.ModelFactory;

import lexic.Alex;
import sintax.$;
import sintax.MistakeLog;
import sintax.Query;

@SuppressWarnings("serial")
public class Interface extends JFrame{
	
	static public void main(String argv[]) 
	{
		try{
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					new Interface();
				}
			});
		}catch(Exception ex){}
	 }

	Color blue;
	JSplitPane mainPane;
	private Interface()
	{
		try{
			super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			super.setTitle("SPARQL Interpretor");
			super.setVisible(true);
			super.setSize(500, 500);
			super.setExtendedState(MAXIMIZED_BOTH);	
		}catch(Exception ex){};

		blue = new Color(210,210,240);
		
		mainPane= new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, initList(),initRight());
		mainPane.setDividerSize(5);
		
		if(docList.getModel().getSize()>0)
			mainPane.setDividerLocation(200);
		else
			mainPane.setDividerLocation(1);
		
		this.add(mainPane);
	}
	
	//////////////////////////////////////////////////////////////////////
	/*
	 * LEFT LIST
	 */
	public static File 	current_file= null;
	JList<File> 		docList;
	
	private Component initList()
	{
		
		if(docList==null)
		{
			docList=new JList<File>();
			
			docList.addMouseListener(new MouseListener() {
				public void mouseReleased(MouseEvent e) {
					if (e.getClickCount()==2)
					{
						current_file= docList.getSelectedValue();
						
						try{
							BufferedReader reader = new BufferedReader(new FileReader(current_file));
							String content="";
							
							String s=reader.readLine();
							if(s!=null)
								content+=s;
							
							while(true)
							{
								s=reader.readLine();
								if(s==null)
									break;
								content+="\n"+s;
							}
							
							text.setText(content);
							document.setText(current_file.toString());
							
						}catch(Exception ex){}
					}
				}
				
				public void mousePressed(MouseEvent e) {}
				public void mouseExited(MouseEvent e) {}
				public void mouseEntered(MouseEvent e) {}
				public void mouseClicked(MouseEvent e) {}
			});
		}
		else
			docList.removeAll();
		
		File dir= new File("SparqlFiles/");
		if(!dir.exists())
			dir.mkdir();
		
		File[] files = dir.listFiles();
		
		docList.setListData(files);
		
		return docList;
	}
	
	//////////////////////////////////////////////////////////////////////
	/*
	* RIGHT LIST
	*/
	JLabel	 			document;
	JTextPane			text;
	JTextPane			outline;
	JTextPane			results;
	JTabbedPane 		tabs;
	
	private Component initRight()
	{
		JSplitPane upperPane= new JSplitPane(JSplitPane.VERTICAL_SPLIT, initButtons(),initText());
		upperPane.setDividerSize(0);
		upperPane.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		
		JSplitPane hPane= new JSplitPane(JSplitPane.VERTICAL_SPLIT, upperPane,initDown());
		hPane.setDividerSize(5);
		hPane.setDividerLocation(400);
		hPane.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		
		JButton new_button= new JButton("new *");
		new_button.setBackground(Color.white);
		new_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				text.setText("");
				document.setText("query");
			}
		});
		
		JPanel new_pane= new JPanel(new FlowLayout(2));
		new_pane.add(new_button);
		
		JSplitPane pane= new JSplitPane(JSplitPane.VERTICAL_SPLIT,new_pane,hPane);
		pane.setDividerSize(0);
		pane.setBorder(null);
		
		return pane;
	}
	
	private Component initButtons()
	{
		document = new JLabel("query");
		document.setBorder(BorderFactory.createMatteBorder(0, 15, 0, 0, Color.white));
		document.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
		document.setBackground(Color.white);
		document.setOpaque(true);
		
		JButton check_button= new JButton(" EXECUTE ");
		check_button.setBackground(blue);
		
		JPanel pane_r= new JPanel(new FlowLayout(2));
		pane_r.add(check_button);
		pane_r.setBackground(blue);
		
		check_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				check();
			}
		});
		
		JPanel pane= new JPanel(new GridLayout(1,0));
		pane.add(document);
		pane.add(pane_r);
		pane.setBorder(BorderFactory.createMatteBorder(0, 0, 5, 0, blue));
		return pane;
	}
	
	private Component initText()
	{
		text= new JTextPane();
		text.setEditable(true);
		JScrollPane scroll= new JScrollPane(text);
		
		text.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) {}
			public void keyReleased(KeyEvent e) {}
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==10 && e.isControlDown())
				{
					e.consume();
					check();
				}
			}
		});
		
		text.setBackground(blue);
		text.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		scroll.setBorder(null);
		
		return scroll;
	}
	
	private Component initDown()
	{
		tabs = new JTabbedPane();
		tabs.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		
		tabs.add(initOutline());
		tabs.add(initResults());
		
		return tabs;
	}
	
	private Component initOutline()
	{		
		outline= new JTextPane();
		outline.setEditable(true);
		JScrollPane scroll= new JScrollPane(outline);
		
		outline.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		outline.setEditable(false);
		scroll.setBorder(null);
		scroll.setName("Outline");
		
		return scroll;
	}
	
	private Component initResults()
	{
		results= new JTextPane();
		results.setEditable(true);
		JScrollPane scroll= new JScrollPane(results);
		
		results.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		results.setEditable(false);
		scroll.setBorder(null);
		scroll.setName("Results");
		
		return scroll;
	}
	
	/////////////////////////////////////////////////////////////////////////////
	/*
	 * METHODS
	 */
	public void check()
	{
		/*
		 * file management
		 */
		String path = document.getText();
		File f = new File(path);
		
		if (path.contentEquals("query"))
		{
			int index=0;
			while(true)
			{
				f = new File("SparqlFiles/"+path+index+".sparql");
				if(!f.exists())
					break;
				index++;
			}
		}
		
		String content= text.getText();
		try{
			
			BufferedWriter writer = new BufferedWriter(new FileWriter(f));
			writer.write(content);
			writer.close();
			
		}catch(Exception ex){}
		
		/*
		 * checking
		 */
		
		try{
			outline.setText(run_checking(new FileInputStream(f)));
		}catch(Exception ex){outline.setText("An error has ocurred while checking ;(\n\n"+ex.getMessage());}
		
		/*
		 * closure
		 */
		
		current_file= f;
		document.setText(f.toString());
		initList();
		if(docList.getModel().getSize()>0)
			mainPane.setDividerLocation(200);
		else
			mainPane.setDividerLocation(1);
		
	}
	
	private String run_checking(InputStream inputStream) throws IOException
	{
		MistakeLog.reset();
		Reader      reader      = new InputStreamReader(inputStream,"Cp1252");
		$.alex = new Alex(reader);
	       
	    $.next(); 	
	       
		Query analizer = new Query();
		OntModel model = ModelFactory.createOntologyModel( OntModelSpec.OWL_MEM_MICRO_RULE_INF);
				
		if(analizer.analize() && MistakeLog.mistakesLog.isEmpty())
		{
			QueryExecution qe = QueryExecutionFactory.create(Query.query, model);
			ResultSet ans = qe.execSelect();
			
			results.setText(ResultSetFormatter.asText(ans));
			tabs.setSelectedIndex(1);
			
			return "ACCEPTED EXPRESSION =D";
		}
		else
		{
			results.setText("");
			tabs.setSelectedIndex(0);
			return "REJECTED EXPRESSION D=\n\nLOG\n"+MistakeLog.report();
		}
	}
}
