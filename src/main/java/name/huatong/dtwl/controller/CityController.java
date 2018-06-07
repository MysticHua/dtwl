package name.huatong.dtwl.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import name.huatong.dtwl.page.table.PageTableRequest;
import name.huatong.dtwl.page.table.PageTableHandler;
import name.huatong.dtwl.page.table.PageTableResponse;
import name.huatong.dtwl.page.table.PageTableHandler.CountHandler;
import name.huatong.dtwl.page.table.PageTableHandler.ListHandler;
import name.huatong.dtwl.dao.CityDao;
import name.huatong.dtwl.model.City;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/citys")
public class CityController {

    @Autowired
    private CityDao cityDao;

    @PostMapping
    @ApiOperation(value = "保存")
    public City save(@RequestBody City city) {
        cityDao.save(city);

        return city;
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id获取")
    public City get(@PathVariable Long id) {
        return cityDao.getById(id);
    }

    @PutMapping
    @ApiOperation(value = "修改")
    public City update(@RequestBody City city) {
        cityDao.update(city);

        return city;
    }

    /*@GetMapping
    @ApiOperation(value = "列表")
    public PageTableResponse list(PageTableRequest request) {
        return new PageTableHandler(new CountHandler() {

            @Override
            public int count(PageTableRequest request) {
                return cityDao.count(request.getParams());
            }
        }, new ListHandler() {

            @Override
            public List<City> list(PageTableRequest request) {
                return cityDao.list(request.getParams(), request.getOffset(), request.getLimit());
            }
        }).handle(request);
    }*/

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除")
    public void delete(@PathVariable Long id) {
        cityDao.delete(id);
    }
}
