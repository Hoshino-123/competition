import request from '@/utils/request';
// list
export const xuexiaoxinxiList = (params) => {
    return request({
        url: '/xuexiaoxinxi/page',
        method: 'post',
        data: params,
    })
};
export const xuexiaoxinxiDetail = (id) => {
    return request({
        url: '/xuexiaoxinxi/'+id,
        method: 'get',
    })
};
// add
export const xuexiaoxinxiSave = (params) => {
    return request({
        url: '/xuexiaoxinxi',
        method: 'post',
        data: params,
    })
};
// updt
export const xuexiaoxinxiEdit = (params) => {
    return request({
        url: '/xuexiaoxinxi',
        method: 'put',
        data: params,
    })
};
// delete
export const xuexiaoxinxiDelete = (id) => {
    return request({
        url: '/xuexiaoxinxi/'+id,
        method: 'delete',
    })
};
// pldel
export const xuexiaoxinxiDeleteList = (xuexiaoxinxis) => {
    return request({
        url: '/xuexiaoxinxi/deleteList',
        data:{list:xuexiaoxinxis},
        method: 'post',
    })
};
// all
export const xuexiaoxinxiAllList = () => {
    return request({
        url: '/xuexiaoxinxi',
        method: 'get',
    })
};

