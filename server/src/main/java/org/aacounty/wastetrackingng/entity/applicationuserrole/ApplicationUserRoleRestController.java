package org.aacounty.wastetrackingng.entity.applicationuserrole;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api/role")
public class ApplicationUserRoleRestController {

	@Autowired
	private ApplicationUserRoleService RoleService;

	@GetMapping
	public List<role> getAllRole() {
		System.out.print("here");
		return RoleService.getAllRole();

	}
}
