import request from '@/utils/request';
// list
export const shenqingtuanduiList = (params) => {
    return request({
        url: '/shenqingtuandui/page',
        method: 'post',
        data: params,
    })
};
export const shenqingtuanduiDetail = (id) => {
    return request({
        url: '/shenqingtuandui/'+id,
        method: 'get',
    })
};
// add
export const shenqingtuanduiSave = (params) => {
    return request({
        url: '/shenqingtuandui',
        method: 'post',
        data: params,
    })
};
// updt
export const shenqingtuanduiEdit = (params) => {
    return request({
        url: '/shenqingtuandui',
        method: 'put',
        data: params,
    })
};
// delete
export const shenqingtuanduiDelete = (id) => {
    return request({
        url: '/shenqingtuandui/'+id,
        method: 'delete',
    })
};
// pldel
export const shenqingtuanduiDeleteList = (shenqingtuanduis) => {
    return request({
        url: '/shenqingtuandui/deleteList',
        data:{list:shenqingtuanduis},
        method: 'post',
    })
};
// all
export const shenqingtuanduiAllList = () => {
    return request({
        url: '/shenqingtuandui',
        method: 'get',
    })
};

