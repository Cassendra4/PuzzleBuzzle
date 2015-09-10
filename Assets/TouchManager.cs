using UnityEngine;
using System.Collections;

public class TouchManager : MonoBehaviour {
	Rigidbody2D grabbedPeice = null;
	// Use this for initialization
	void Start () {
	
	}
	
	// Update is called once per frame
	void Update () {
		if (Input.GetMouseButtonDown (0)) {
			Vector3 worldpos = Camera.main.ScreenToWorldPoint (Input.mousePosition);
			Vector2 mousepos2d = new Vector2 (worldpos.x, worldpos.y);
			Vector2 dir = Vector2.zero;

			RaycastHit2D hit = Physics2D.Raycast (mousepos2d, dir);
			if (hit != null && hit.collider != null) {
				if(hit.collider.attachedRigidbody != null){
					grabbedPeice = hit.collider.attachedRigidbody;
					grabbedPeice.transform.localScale *= 1.2f;
				}
			}
		}
		if (Input.GetMouseButtonUp (0)) {
			grabbedPeice.transform.localScale /= 1.2f;
			grabbedPeice = null;
		}
	}
	void FixedUpdate(){
		if (grabbedPeice != null) {
			Vector3 worldpos = Camera.main.ScreenToWorldPoint(Input.mousePosition);
			Vector2 mousepos2d = new Vector2 (worldpos.x, worldpos.y);
			grabbedPeice.position = mousepos2d;

		}
	}
}
