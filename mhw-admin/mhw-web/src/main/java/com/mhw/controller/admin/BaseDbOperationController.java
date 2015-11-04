package com.mhw.controller.admin;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import com.google.common.net.HttpHeaders;
import com.mhw.controller.BaseWebController;
import com.mhw.entity.BaseEntity;
import com.mhw.exception.AdminException;
import com.mhw.form.admin.BaseDbOperationForm;

public abstract class BaseDbOperationController<F extends BaseDbOperationForm<E>, E extends BaseEntity, ID extends Serializable>
	extends BaseWebController {
	
	protected abstract String getPath();
	
	protected abstract String getTemplatePath();
	
	protected abstract JpaRepository<E, ID> getRepository();
	
	protected abstract Class<E> getClazz();
	
	/**
	 * 一覧
	 * 
	 * @param model
	 * @return
	 */
	public String index(Pageable pageable, F form, Model model) {
		model.addAttribute("entities", this.getRepository().findAll(pageable));
		model.addAttribute("fields", this.getClazz().getDeclaredFields());
		return this.getTemplatePath() + "/" + this.getPath() + "/index";
	}
	
	/**
	 * 詳細・更新・削除
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	public String detail(ID id, F form, Model model) {
		model.addAttribute("entity", this.getRepository().findOne(id));
		model.addAttribute("fields", this.getClazz().getDeclaredFields());
		return this.getTemplatePath() + "/" + this.getPath() + "/detail";
	}
	
	/**
	 * 登録
	 * 
	 * @param form
	 * @param model
	 * @return
	 */
	public String save(F form, RedirectAttributesModelMap model) {
		this.getRepository().save(form.getEntity());
		return "redirect:/" + this.getTemplatePath() + "/" + this.getPath();
	}
	
	/**
	 * 更新
	 * 
	 * @param id
	 * @param form
	 * @param model
	 * @return
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 */
	public String update(ID id, F form, RedirectAttributesModelMap model) throws InstantiationException,
		IllegalAccessException {
		E entity = this.getRepository().findOne(id);
		E input = form.getEntity();
		BeanUtils.copyProperties(input, entity, new String[] { "id", "createAt" });
		this.getRepository().save(entity);
		return "redirect:/" + this.getTemplatePath() + "/" + this.getPath() + "/" + id;
	}
	
	/**
	 * 削除
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	public String delete(ID id, RedirectAttributesModelMap model) {
		// FIXME 外部キー制約
		this.getRepository().delete(id);
		return "redirect:/" + this.getTemplatePath() + "/" + this.getPath();
	}
	
	@ExceptionHandler(AdminException.class)
	public String handleAdminException(HttpServletRequest req, Exception e) throws UnsupportedEncodingException {
		
		String queryString = req.getQueryString();
		if (queryString == null) {
			queryString = "";
		}
		
		return "redirect:" + req.getHeader(HttpHeaders.REFERER) + "?error=" + URLEncoder.encode(e.getMessage(), "UTF-8") + "&" + queryString;
	}
	
}