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

import com.mhw.entity.TEvaluation;
import com.mhw.form.admin.TEvaluationForm;
import com.mhw.repository.TEvaluationRepository;

@Controller
@RequestMapping(value = "admin")
public class AdminTEvaluationController extends BaseDbOperationController<TEvaluationForm, TEvaluation, Long> {
	
	private final String path = "tEvaluation";
	
	@Autowired
	private TEvaluationRepository repository;
	
	@Override
	protected String getPath() {
		return path;
	}
	
	@Override
	protected String getTemplatePath() {
		return "admin";
	}
	
	@Override
	protected JpaRepository<TEvaluation, Long> getRepository() {
		return this.repository;
	}
	
	@Override
	protected Class<TEvaluation> getClazz() {
		return TEvaluation.class;
	}
	
	@Override
	@RequestMapping(value = path, method = RequestMethod.GET)
	public String index(@PageableDefault(size = 100, sort = "id", direction = Direction.DESC) Pageable pageable, TEvaluationForm form,
		Model model) {
		return super.index(pageable, form, model);
	}
	
	@Override
	@RequestMapping(value = path + "/{id}", method = RequestMethod.GET)
	public String detail(@PathVariable Long id, TEvaluationForm form, Model model) {
		return super.detail(id, form, model);
	}
	
	@Override
	@RequestMapping(value = path, method = RequestMethod.POST)
	public String save(@ModelAttribute TEvaluationForm form, RedirectAttributesModelMap model) {
		return super.save(form, model);
	}
	
	@Override
	@RequestMapping(value = path + "/{id}", method = RequestMethod.PUT)
	public String update(@PathVariable Long id, TEvaluationForm form, RedirectAttributesModelMap model)
		throws InstantiationException, IllegalAccessException {
		return super.update(id, form, model);
	}
	
	@Override
	@RequestMapping(value = path + "/{id}", method = RequestMethod.DELETE)
	public String delete(@PathVariable Long id, RedirectAttributesModelMap model) {
		return super.delete(id, model);
	}
}
