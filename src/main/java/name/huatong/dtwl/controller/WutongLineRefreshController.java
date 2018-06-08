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
import name.huatong.dtwl.dao.WutongLineRefreshDao;
import name.huatong.dtwl.model.WutongLineRefresh;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/wutongLineRefreshs")
public class WutongLineRefreshController {

    @Autowired
    private WutongLineRefreshDao wutongLineRefreshDao;

    @PostMapping
    @ApiOperation(value = "保存")
    public WutongLineRefresh save(@RequestBody WutongLineRefresh wutongLineRefresh) {
        wutongLineRefreshDao.save(wutongLineRefresh);

        return wutongLineRefresh;
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id获取")
    public WutongLineRefresh get(@PathVariable Long id) {
        return wutongLineRefreshDao.getById(id);
    }

    @PutMapping
    @ApiOperation(value = "修改")
    public WutongLineRefresh update(@RequestBody WutongLineRefresh wutongLineRefresh) {
        wutongLineRefreshDao.update(wutongLineRefresh);

        return wutongLineRefresh;
    }

    @GetMapping
    @ApiOperation(value = "列表")
    public PageTableResponse list(PageTableRequest request) {
        return new PageTableHandler(new CountHandler() {

            @Override
            public int count(PageTableRequest request) {
                return wutongLineRefreshDao.count(request.getParams());
            }
        }, new ListHandler() {

            @Override
            public List<WutongLineRefresh> list(PageTableRequest request) {
                return wutongLineRefreshDao.list(request.getParams(), request.getOffset(), request.getLimit());
            }
        }).handle(request);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除")
    public void delete(@PathVariable Long id) {
        wutongLineRefreshDao.delete(id);
    }
}
