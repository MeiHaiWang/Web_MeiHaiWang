package com.mhw.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import com.mhw.entity.TMenu;
import com.mhw.form.admin.TMenuForm;
import com.mhw.repository.TMenuRepository;

@Controller
@RequestMapping(value = "admin")
public class AdminTMenuController extends BaseDbOperationController<TMenuForm, TMenu, Long> {
	
	private final String path = "tMenu";
	
	@Autowired
	private TMenuRepository repository;
	
	@Override
	protected String getPath() {
		return path;
	}
	
	@Override
	protected String getTemplatePath() {
		return "admin";
	}
	
	@Override
	protected JpaRepository<TMenu, Long> getRepository() {
		return this.repository;
	}
	
	@Override
	protected Class<TMenu> getClazz() {
		return TMenu.class;
	}
	
	@Override
	@RequestMapping(value = path, method = RequestMethod.GET)
	public String index(@PageableDefault(size = 100, sort = "id", direction = Direction.DESC) Pageable pageable, TMenuForm form,
		Model model) {
		return super.index(pageable, form, model);
	}
	
	@Override
	@RequestMapping(value = path + "/{id}", method = RequestMethod.GET)
	public String detail(@PathVariable Long id, TMenuForm form, Model model) {
		return super.detail(id, form, model);
	}
	
	@Override
	@RequestMapping(value = path, method = RequestMethod.POST)
	public String save(@ModelAttribute TMenuForm form, RedirectAttributesModelMap model) {
		return super.save(form, model);
	}
	
	@Override
	@RequestMapping(value = path + "/{id}", method = RequestMethod.PUT)
	public String update(@PathVariable Long id, TMenuForm form, RedirectAttributesModelMap model)
		throws InstantiationException, IllegalAccessException {
		return super.update(id, form, model);
	}
	
	@Override
	@RequestMapping(value = path + "/{id}", method = RequestMethod.DELETE)
	public String delete(@PathVariable Long id, RedirectAttributesModelMap model) {
		return super.delete(id, model);
	}
}
