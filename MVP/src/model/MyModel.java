package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.WriteAbortedException;
import java.io.Writer;
import java.net.NetworkInterface;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InvalidPropertiesFormatException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Observable;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import javax.naming.directory.DirContext;
import javax.print.attribute.ResolutionSyntax;
import javax.xml.bind.JAXB;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import algorithms.demo.Maze3dSearchableAdapter;
import algorithms.mazeGenerators.GrowingTreeGenerator;
import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
import algorithms.mazeGenerators.RandomPositionChooser;
import algorithms.search.BFS;
import algorithms.search.DFS;
import algorithms.search.Solution;
import algorithms.search.State;
import io.MyCompressorOutputStream;
import io.MyDecompressorInputStream;
import presenter.Controller;
import presenter.Properties;

/**
 * The Class MyModel.
 */
public class MyModel extends Observable implements Model {
	
	/** The saved maze Map. */
	Map<String, Maze3d> savedMaze;
	
	/** The solutions Map. */
	HashMap<String, Solution<Position>> solutions;
	
	/** The maze solution. */
	HashMap<Maze3d, Solution<Position>> mazeSolution;
	
	/** The exec service. */
	private ExecutorService execService;
	
	/** The controller. */
	Controller controller;
	
	/** The generation type. */
	String generationType;
	
	/** The solving algorithm. */
	String solvingAlgorithm;
	
	/** The max threads. */
	int maxThreads;
	
	/** The view style. */
	String viewStyle;

	/**
	 * Instantiates a new my model.
	 */
	public MyModel() {
		savedMaze = new ConcurrentHashMap<>();
		solutions = new HashMap<>();
		this.execService = Executors.newFixedThreadPool(20);
		this.mazeSolution = new HashMap<>();
		loadProperties("./Properties.xml");		
		loadCache();
	}
	
	/**
	 * Instantiates a new my model.
	 *
	 * @param controller the controller
	 */
	public MyModel(Controller controller) {
		this.controller = controller;
		savedMaze = new ConcurrentHashMap<>();
		solutions = new HashMap<>();
		this.execService = Executors.newFixedThreadPool(20);
		this.mazeSolution = new HashMap<>();
		loadProperties("./Properties.xml");
	}

	/* (non-Javadoc)
	 * @see model.Model#generateMaze(java.lang.String, int, int, int)
	 */
	@Override
	public void generateMaze(String name, int floors, int height, int width) {
		Callable<Maze3d> thread = new Callable<Maze3d>() {

			@Override
			public Maze3d call() throws Exception {
				if (name!=null){
					GrowingTreeGenerator generator = new GrowingTreeGenerator(new RandomPositionChooser());
					Maze3d maze = generator.generate(floors,height, width);
					savedMaze.put(name, maze);
					
					setChanged();
					notifyObservers("Maze " + "'"+ name + "' is READY!");
					return maze;				
				}
				else{
					setChanged();
					notifyObservers("invalid data");
					return null;
				}
			}	
		};
		
		Future<Maze3d> future = execService.submit(thread);
		while(!future.isDone()){}
		setChanged();
		try {
			if(future.get()!=null)
				notifyObservers("Thread Finished");
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		
	}

	/* (non-Javadoc)
	 * @see model.Model#getMaze(java.lang.String)
	 */
	@Override
	public Maze3d getMaze(String name) {
		return savedMaze.get(name);
	}

	/* (non-Javadoc)
	 * @see model.Model#saveMaze(java.lang.String, java.lang.String)
	 */
	@Override
	public void saveMaze(String name, String file_name) {
		
		Maze3d maze = getMaze(name);
		
		try {
			OutputStream out=new MyCompressorOutputStream(
					new FileOutputStream(file_name));
					try {
						out.write(maze.toByteArray());
						out.flush();
						setChanged();
						notifyObservers("Maze has been saved successfully!");
					} catch (IOException e) {
						setChanged();
						notifyObservers("There was an error saving the maze.");
					}
					finally {
						try {
							out.close();
						} catch (IOException e) {
							setChanged();
							notifyObservers("Error saving the file!");
						}
					}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	/* (non-Javadoc)
	 * @see model.Model#loadMaze(java.lang.String, java.lang.String)
	 */
	@Override
	public void loadMaze(String file_name, String name) {
		
		if(!savedMaze.containsKey(name)) {
			try {
				InputStream in=new MyDecompressorInputStream(
						new FileInputStream(file_name));
				InputStream in2=new MyDecompressorInputStream(
						new FileInputStream(file_name));
				
				Scanner scanner = new Scanner(in);
				Scanner input = scanner.useDelimiter(",");
				byte[] b = new byte[Integer.valueOf(input.next())];
				
				try {
					in2.read(b);
					Maze3d loaded=new Maze3d(b);	
					savedMaze.put(name,loaded);
					setChanged();
					notifyObservers("Maze " + name + " has been loaded successfully!");
				} catch (IOException e) {
					e.printStackTrace();
				}
				finally {
					try {
						in.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
					setChanged();
					notifyObservers("Maze loaded successfuly!");
				}
			} catch (FileNotFoundException e) {
				setChanged();
				notifyObservers("Error loading your maze.");
				e.printStackTrace();
			}
		}
		else {
			setChanged();
			notifyObservers("Maze already exists!");
		}
		
	}

	/* (non-Javadoc)
	 * @see model.Model#solveMaze(java.lang.String, java.lang.String)
	 */
	@Override
	public void solveMaze(String name, String algorithm) {
		
		Callable<Solution<Position>> thread = new Callable<Solution<Position>>() {

			@Override
			public Solution<Position> call() throws Exception {
				
				if(name!= null && algorithm != null) {
					if(savedMaze.containsKey(name)) {
						if(mazeSolution.containsKey(savedMaze.get(name))) {
							setChanged();
							notifyObservers("Solution already exists.");
							return mazeSolution.get(savedMaze.get(name));
						}
						
						Maze3d maze = getMaze(name);
						Solution<Position> solution = new Solution<>();
						Maze3dSearchableAdapter mazeAdapter = new Maze3dSearchableAdapter(maze);
						
						if(algorithm.equals("BFS") || algorithm.equals("bfs")) {
							BFS<Position> searcher = new BFS<>();
							solution = searcher.search(mazeAdapter);
							solutions.put(name, solution);
						}
						else if (algorithm.equals("DFS") || algorithm.equals("dfs")) {
							DFS<Position> searcher = new DFS<>();
							solution = searcher.search(mazeAdapter);
							solutions.put(name, solution);
						}else {
							setChanged();
							notifyObservers("Wrong method. Available options are BFS/DFS");
							return null;
						}
						
						mazeSolution.put(savedMaze.get(name), solution);
						setChanged();
						notifyObservers("Maze " + name + " has been solved!");
						return solution;
					}
					else {
						setChanged();
						notifyObservers("Maze not found!");
						return null;
					}
				}
				else {
					setChanged();
					notifyObservers("Wrong input!");
					return null;
				}
			}

		};
		

		Future<Solution<Position>> future = execService.submit(thread);
		while(!future.isDone()){}
		setChanged();
		try {			
			if(future.get()!=null) {
				notifyObservers("Thread Finished");
			}
			saveCache();
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		
	}
	
	/* (non-Javadoc)
	 * @see model.Model#getSolution(java.lang.String)
	 */
	public Solution<Position> getSolution(String name) {
		return solutions.get(name);
	}

	/* (non-Javadoc)
	 * @see model.Model#listFiles(java.lang.String)
	 */
	@Override
	public File[] listFiles(String path) {
		if(path!=null) {
			File file = new File(path);
			if(file.isDirectory() && file.exists()) {
				return (new File(path).listFiles());
			}
			else {
				setChanged();
				notifyObservers("ERROR:" + path +" does not exist!");
				return null;
			}
		}
		else {
			setChanged();
			notifyObservers("Path cannot be NULL!");
			return null;
		}
		
	}

	/* (non-Javadoc)
	 * @see model.Model#setController(controller.Controller)
	 */
	@Override
	public void setController(Controller controller) {
		this.controller = controller;
	}
	
	/* (non-Javadoc)
	 * @see model.Model#exit(java.lang.String[])
	 */
	public void exit(String[] args) {
		execService.shutdown();
		setChanged();
		notifyObservers("Closing Application . . .");
		System.exit(1);
	}
	
	/**
	 * Save cache.
	 */
	private void saveCache() {
		ObjectOutputStream oos = null;
		
		try {
			oos = new ObjectOutputStream(
					new GZIPOutputStream(
					new FileOutputStream("Solutions.zip")));
			oos.writeObject(mazeSolution);
			System.out.println("Cache was saved successfully!");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				oos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	
	/**
	 * Load cache.
	 */
	@SuppressWarnings("unchecked")
	private void loadCache() {
		File file = new File("Solutions.zip");
		if(!file.exists() | !file.canRead())
			return;
		
		ObjectInputStream ois = null;
		
		try {
			ois = new ObjectInputStream(
					new GZIPInputStream(
					new FileInputStream("Solutions.zip")));
			
			mazeSolution = (HashMap<Maze3d, Solution<Position>>) ois.readObject();
			System.out.println("Cache loaded successfully!");
			//System.out.println(mazeSolution);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally{
			try {
				ois.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}		
		
	}
	
	/* (non-Javadoc)
	 * @see model.Model#loadProperties()
	 */
	public void loadProperties(String fileName) {
			Properties properties = new Properties();
			FileInputStream xml;
			
			try {
				xml = new FileInputStream(fileName);
				properties = JAXB.unmarshal(xml, Properties.class);
				this.generationType = properties.getGenerationType();
				this.solvingAlgorithm = properties.getSolvingAlgorithm();
				this.maxThreads = properties.getMaxThreads();
				System.out.println("Properties loaded successfully!");
			} catch (Exception e) {
				setChanged();
				System.out.println("Error loading properties! :(");
				e.printStackTrace();
			}
	}

	/* (non-Javadoc)
	 * @see model.Model#saveProperties()
	 */
	@Override
	public void saveProperties() {
		
		presenter.Properties pro = new presenter.Properties(this.generationType,this.solvingAlgorithm,this.maxThreads,this.viewStyle);
		Properties properties=new Properties();
		
		FileOutputStream xml;
		
		try {
			xml = new FileOutputStream("Properties.xml");
			JAXB.marshal(pro, xml);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}

	/* (non-Javadoc)
	 * @see model.Model#editProperties(java.lang.String, java.lang.String, java.lang.Integer, java.lang.String)
	 */
	@Override
	public void editProperties(String generationType, String solutionAlgorithm, Integer maxThreads, String viewStyle) {
		if (generationType!=null && solutionAlgorithm!=null && maxThreads!=null && viewStyle!=null )
		{
			if (generationType!=null)
				this.generationType=generationType;
			if (solutionAlgorithm!=null)
				this.solvingAlgorithm=solutionAlgorithm;
			if (maxThreads!=null) {
				this.maxThreads = maxThreads;
				this.execService = Executors.newFixedThreadPool(maxThreads);
			}
			if(viewStyle!=null)
				this.viewStyle=viewStyle;
				
			saveProperties();
		}
		else{
			setChanged();
			notifyObservers("Error: the maze name or file name is empty");
		}
		
	}
	
	/**
	 * get Cross Section
	 * axle index maze name.
	 *
	 * @param name the name
	 * @param axis the axis
	 * @param floor the floor
	 * @return the cross section
	 */
	public int[][] getCrossSection(String name, String axis, Integer floor){
		if (axis!=null && floor!=null && name!=null){
			Maze3d maze=this.savedMaze.get(name);
			if (maze!=null)
				if(axis.toUpperCase().equals("X"))
					return maze.getCrossSectionByX(floor);
				else if(axis.toUpperCase().equals("Y"))
					return maze.getCrossSectionByY(floor);
				else if(axis.toUpperCase().equals("Z"))
					return maze.getCrossSectionByZ(floor);
				else{
					setChanged();
					notifyObservers("ERROR: the axle dosen't exist");
					return null;
				}
			else{
				setChanged();
				notifyObservers("ERROR: the maze does not exist!");
				return null;
			}
		}
		else{
			setChanged();
			notifyObservers("ERROR: the axis of the floor or the maze name is empty!");
			return null;
		}
		
	}
}