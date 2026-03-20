import request from '@/utils/request';
// list
export const jingsaileixingList = (params) => {
    return request({
        url: '/jingsaileixing/page',
        method: 'post',
        data: params,
    })
};
export const jingsaileixingDetail = (id) => {
    return request({
        url: '/jingsaileixing/'+id,
        method: 'get',
    })
};
// add
export const jingsaileixingSave = (params) => {
    return request({
        url: '/jingsaileixing',
        method: 'post',
        data: params,
    })
};
// updt
export const jingsaileixingEdit = (params) => {
    return request({
        url: '/jingsaileixing',
        method: 'put',
        data: params,
    })
};
// delete
export const jingsaileixingDelete = (id) => {
    return request({
        url: '/jingsaileixing/'+id,
        method: 'delete',
    })
};
// pldel
export const jingsaileixingDeleteList = (jingsaileixings) => {
    return request({
        url: '/jingsaileixing/deleteList',
        data:{list:jingsaileixings},
        method: 'post',
    })
};
// all
export const jingsaileixingAllList = () => {
    return request({
        url: '/jingsaileixing',
        method: 'get',
    })
};

