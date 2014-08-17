package com.oxistudios.charlie.lighting_engine;

import com.badlogic.gdx.Gdx;

public class LightController {
	static final Color defaultColor = new Color(0.75f, 0.75f, 0.5f, 0.75f);
	private boolean activeLight = true;
	private boolean softLight = true;
	private boolean xrayLight = false;
	protected boolean staticLight = false;
	protected float softLightShadowLength = 2.5f;
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

	public LightController(RayHandler rayHandler, int rays, Color color,
			float directionDegree, float distance) {
		rayHandler.lightList.add(this);
		this.rayHandler = rayHandler;
		setRayNum(rays);
		this.direction = directionDegree;
		distance *= RayHandler.gammaCorrectionParameter;
		this.distance = distance < 0.01f ? 0.01f : distance;
		setColor(color);
	}

	public void setcolor(Color newColor) {
		if (newColor != null) {
			color.set(newColor);
			colorF = color.toFloatBits();
		} else {
			color = defaultColor;
			colorF = defaultColor.toFloatBits();
		}
		if (staticLight) {
			staticUpdate();
		}
	}

	public void setColor(float f, float g, float b, float a) {
		// float a = intensity
		color.set(r, g, b, a);
		colorF = color.toFloatBits();
		if (staticLight) {
			staticUpdate();
		}
	}

	public void setDistance(float dist) {

	}

	abstract void update();

	abstract void render();

	public abstract void setDirection(float directionDegree);

	public void remove() {
		if (active) {
			rayHandler.lightList.removeValue(this, false);
		} else {
			rayHandler.disabledLights.removeValue(this, false);
		}
		dispose();
	}

	public void dispose() {
		lightMesh.dispose();
		softShadowMesh.dispose();
	}

	public abstract void attatchToCharlieBody(Body body, float offsetX,
			float offsetY);

	// attatches light to automatically follow Charlie

	public abstract CharlieBody getCharlieBody();

	public abstract void setPosition(float x, float y);

	// sets the starting position of the light

	public abstract void setPosition(Vector2 position);

	final Vector2 tmpPosition = new Vector2();

	// starting position of the light in the coordinates of the level
	public Vector2 getPosition() {
		return tmpPosition;
	}

	public abstract float getX();

	// horizontal starting position of Light in the coordinates of the level
	public abstract float getY();

	// vertical starting position of Light in the coordinates of the level
	void staticUpdate() {
		boolean tmp = rayhandler.culling;
		staticLight = !staticLight;
		rayHandler.culling = false;
		update();
		rayHandler.culling = tmp;
		staticLight = !staticLight;
	}

	public final boolean isActive() {
		return active;
	}

	public final void setActive(boolean active) {
		// disables and enables the light updating and rendering
		if (active == this.active) {
			return;
		}
		if (active) {
			rayHandler.lightList.add(this);
			rayHandler.disabledLights.removeValue(this, true);
		}
		this.active = active;
	}

	public final boolean isXrayLight() {
		// decides if light beams go through certian obstacles
		return xrayLight;
	}

	public final void setXray(boolean xray) {
		// disables and enables xray beams. Enabled makes the light to go
		// through obstacles, disabled makes light stop at obstacles and not
		// travel though. I read that having xray enabled is hard on the cpu so
		// it must be used wisely
		this.xray = xray;
		if (staticLight) {
			staticUpdate();
		}
	}

	public final boolean isStaticLight() {
		// static light does not get any automatic updates but setting any
		// parameter will update it. Static lights are good for lights that you
		// want to collide with static geometry but you should ignore all the
		// dymaic objects with static light.
		return staticLight;
	}

	public final void setStaticLight(boolean staticLight) {
		// disables and enables light staticness
		this.staticLight = staticLight;
		if (staticLight) {
			staticUpdate();
		}
	}

	public final boolean isLightSoft() {
		// are the tips of the light beams soft or hard
		return softLight;
	}

	public final void setLightSoft(boolean softLight) {
		// disables and enables the softness of the light beam tips
		this.softLight = softLight;
		if (staticLight) {
			staticUpdate();
		}
	}

	public final float getLightSoftShadowLength() {
		// returns how much softness is used in the tip of the light beams. The
		// default is set to 2.5
		return softLightShadowLength;
	}

	public final void setLightSoftnessLength(float softLightShadowLength) {
		// set how much softness is used in the tip of the light beams. The
		// default is set to 2.5
		this.softLightShadowLength = softLightShadowLength;
		if (staticLight) {
			staticUpdate();
		}
	}

	private final void setRayNumber(int rays) {
		if (rays < MIN_RAYS) {
			rays = MIN_RAYS;
		}
		rayNum = rays;
		vertexNum = rays + 1;
		segments = new float[vertexNum * 8];
		mx = new float[vertexNum];
		my = new float[vertexNum];
		f = new float[vertexNum];
	}

	static final float zero = Color.toFloatBits(0f, 0f, 0f, 0f);

	public Color getLightColor() {
		// returns the current lights color
		return color;
	}

	public float getLightDistance() {
		// returns the light rays distance
		float dist = distance / rayHandler.gammaCorrectionParameter;
		return dist;
	}

	public boolean contains(float x, float y) {
		// cheking if the given point is inside of the light area
		return false;
	}

	final RayCastCallback ray = new RayCastCallback() {
		@Override
		final public float reportRayFixture(Fixture fixture, Vector2 point,
				Vector2 normal, float fraction) {
			if ((filerA != null) && !contactFilter(fixture)) {
				return 1;
			}
			mx[m_index] = point.x;
			my[m_index] = point.y;
			f[m_index] = fraction;

			return fraction;
		}
	};

	final boolean contactFiler(Fixture fixtureB){
		Filter filterB = fixtureB.getFilterData();
		if(filterA.groupIndex == filterB.groupIndex && filterA.groupIndex != 0){
			return filterA.groupIndex > 0;
		}
		retrun(filterA.maskBits & filterB.categoryBits) != 0 && (filterA.categoryBits & filterB.maskBits) != 0;
	}

	static private Filter filterA = null;

	// light filter

	static public void setContactFilter(Filter filter) {
		// sets the contact filter for all the lights
		filterA = filter;
	}

	static public void setContactFilter(short categoryBits, short groupIndex,
			short maskBits) {
		filterA = new Filter();
		filterA.categoryBits = categoryBits;
		filterA.groupIndex = groupIndex;
		filterA.maskBits = maskBits;
	}

}
