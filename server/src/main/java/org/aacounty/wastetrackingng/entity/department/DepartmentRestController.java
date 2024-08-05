package org.aacounty.wastetrackingng.entity.department;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api/departments")
public class DepartmentRestController {

	@Autowired
	private DepartmentService departmentService;

	@CrossOrigin(origins = "http://localhost:3000")
	@GetMapping
	public List<Department> getAllDepartments() {

		return departmentService.getAllDepartments();
	}
}
