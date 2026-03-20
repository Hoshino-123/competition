import request from '@/utils/request';
// list
export const tuanyuanList = (params) => {
    return request({
        url: '/tuanyuan/page',
        method: 'post',
        data: params,
    })
};
export const tuanyuanDetail = (id) => {
    return request({
        url: '/tuanyuan/'+id,
        method: 'get',
    })
};
// add
export const tuanyuanSave = (params) => {
    return request({
        url: '/tuanyuan',
        method: 'post',
        data: params,
    })
};
// updt
export const tuanyuanEdit = (params) => {
    return request({
        url: '/tuanyuan',
        method: 'put',
        data: params,
    })
};
// delete
export const tuanyuanDelete = (id) => {
    return request({
        url: '/tuanyuan/'+id,
        method: 'delete',
    })
};
// pldel
export const tuanyuanDeleteList = (tuanyuans) => {
    return request({
        url: '/tuanyuan/deleteList',
        data:{list:tuanyuans},
        method: 'post',
    })
};
// all
export const tuanyuanAllList = () => {
    return request({
        url: '/tuanyuan',
        method: 'get',
    })
};

