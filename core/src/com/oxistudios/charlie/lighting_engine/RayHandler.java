package com.oxistudios.charlie.lighting_engine;

import javax.media.j3d.Light;

import com.badlogic.gdx.Gdx;

public class RayHandler implements Disposable {
	// Gamma correction value that is used if it is enabled
	static final float gamma_correction = 0.625f;
	static boolean gammaCorrection = false;
	static float gammaCorrectionParameter = 1f;
	public static boolean isDiffuse = false;

	final Array<Light> lightList = new Array<Light>(false, 16);
	// Array that contains all the lights
	final Array<Light> disabledLights = new Array<Light>(false, 16);
	// Array that contains all the disabled lights
	final LightMap lightMap;
	final ShaderProgram lightShader;

	boolean culling = true;
	boolean shadows = true;
	boolean blur = true;

	int blurNum = 1;

	int viewportX = 0;
	int viewportY = 0;
	int viewPortWidth = Gdx.graphics.getWidth();
	int viewPortHeight = Gdx.graphics.getHeight();

	int lightRenderedLastFrame = 0;

	float x1;
	float x2;
	float y1;
	float y2;
	// Camera matrix corners
	World world;

	public RayHandler(World world) {
		this(world, Gdx.graphics.getWidth() / 4, Gdx.graphics.getHeight() / 4);
	}

	public RayHandler(World world, int fboWidth, int fboHeight) {
		this.world = world;
		lightMap = new LightMap(this, fboWidth, fboHeight);
		lightShader = LightShader.createLightShader();
	}

	public void setCombinedMatrix(Matrix4 combined) {
		// Matrix4 is assumed to be orthogonal for culling and directional
		// lights
		System.arraycopy(combined.val, 0, this.combined.val, 0, 16);
		float invWidth = combined.val[Matrix4.M00];
		// updates the camera corners
		final float halfViewPortWidth = 1f / invWidth;
		final float x = -halfViewPortWidth * combined.val[Matrix4.M03];
		x1 = x - halfViewPortWidth;
		x2 = x + halfViewPortWidth;

		float invHeight = combined.val[Matrix4.M11];

		final float halfViewPortHeight = 1f / invHeight;
		final float y = -halfViewPortHeight * combined.val[Matrix4.M13];
		y1 = y - halfViewPortHeight;
		y2 = y + halfViewPortHeight;
	}

	public void setCombinedMatrix(Matrix4 combined, float x, float y,
			float viewPortWidth, float viewPortHeight) {
		// Sets the combined camera matrix.
		System.arraycopy(combined.val, 0, this.combined.val, 0, 16);
		// updateCameraCorners
		final float halfViewPortWidth = viewPortWidth * 0.5f;
		x1 = x - halfViewPortWidth;
		x2 = x + halfViewPortWidth;

		final float halfViewPortHeight = viewPortHeight * 0.5f;
		y1 = y - halfViewPortHeight;
		y2 = y + halfViewPortHeight;
	}

	boolean intersect(float x, float y, float radius) {
		// Checks to see if light is on the screen. Retrun true if camera screen
		// intersects or contains provided light, represented by circle/box area
		return (x1 < (x + radius) && x2 > (x - radius) && y1 < (y + radius) && y2 > (y - radius));
	}

	public void updateAndRender(){
	// Updates and renders all the active lights
		update();
		render();
	}
	
	public void update(){
	// Manual update method for all the active lights
		for(Light light : lightList){
			light.update();
		}
	
	}
	
	public void render(){
	// Manual render method for all the active lights
		lightRenderedLastFrame = 0;
		
		Gdx.gl.glDepthMask(false);
		Gdx.gl.glEnable(GL20.GL_BLEND);
		Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE);
		
		boolean useLightMap = (shadows || blur);
		if(useLightMap){
			lightMap.frameBuffer.begin();
			Gdx.gl.glClearColor(0f, 0f, 0f, 0f);
			Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		}
		
		
		
	}
	
	
	
	
	
	
	
}
