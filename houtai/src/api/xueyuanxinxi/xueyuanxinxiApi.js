import request from '@/utils/request';
// list
export const xueyuanxinxiList = (params) => {
    return request({
        url: '/xueyuanxinxi/page',
        method: 'post',
        data: params,
    })
};
export const xueyuanxinxiDetail = (id) => {
    return request({
        url: '/xueyuanxinxi/'+id,
        method: 'get',
    })
};
// add
export const xueyuanxinxiSave = (params) => {
    return request({
        url: '/xueyuanxinxi',
        method: 'post',
        data: params,
    })
};
// updt
export const xueyuanxinxiEdit = (params) => {
    return request({
        url: '/xueyuanxinxi',
        method: 'put',
        data: params,
    })
};
// delete
export const xueyuanxinxiDelete = (id) => {
    return request({
        url: '/xueyuanxinxi/'+id,
        method: 'delete',
    })
};
// pldel
export const xueyuanxinxiDeleteList = (xueyuanxinxis) => {
    return request({
        url: '/xueyuanxinxi/deleteList',
        data:{list:xueyuanxinxis},
        method: 'post',
    })
};
// all
export const xueyuanxinxiAllList = () => {
    return request({
        url: '/xueyuanxinxi',
        method: 'get',
    })
};

