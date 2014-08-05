package com.oxistudios.charlie.lighting_engine;

public class LightController {
	static final Color defaultColor = new Color(0.75f, 0.75f, 0.5f, 0.75f);
	private boolean activeLight = true;
	private boolean softLight = true;
	private boolean xrayLight = false;
	protected boolean staticLight = false;
	protected float softShadowLength = 2.5f;
	protected RayHandler rayHandler;
	protected boolean culled = false;
	protected int rayNumber;
	protected int vertexNumber;
	protected float distance;
	protected float direction;
	
	protected Color color = new Color();
	protected Mesh lightMesh;
	protected Mesh softShadowMesh;
	protected float colorF;
	
	final static int miniumum_rays = 3;
	
	float segements[];
	float[] mx;
	float[] my;
	float[] f;
	int m_index = 0;
	
	public LightController(RayHandler rayHandler, int rays, Color color, float directionDegree, float distance){
		rayHandler.lightList.add(this);
		this.rayHandler = rayHandler;
		setRayNum(rays);
		this.direction = directionDegree;
		distance *= RayHandler.gammaCorrectionParameter;
		this.distance = distance < 0.01f ? 0.01f : distance;
		setColor(color);
	}
}
